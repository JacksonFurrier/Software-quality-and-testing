package ucoach.process.endpoint;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import ucoach.business.client.*;
import ucoach.external.RandomJokesClient;
import ucoach.external.TwitterClient;
import ucoach.util.Authorization;
import ucoach.util.JsonParser;

@Path("/measure")
public class HealthMeasureController {

	public static final String MOTIVATIONAL_MESSAGE = "Keep going! Almost there";

	@POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response newMeasure(@Context HttpHeaders headers, String measure) {
		// Build JSON response object
		JSONObject json = new JSONObject();
		
		String userToken = headers.getHeaderString("User-Authorization");
		if (!Authorization.validateRequest(headers) || (userToken == "" || userToken == null)) {
			json.put("status", "401").put("message", "Unauthorize");
			return Response.status(401).entity(json.toString()).build();
		}

		// #1 CREATE NEW MEASURE
		HealthMeasureClient measureClient = new HealthMeasureClient();
		boolean created = measureClient.newHealthMeasure(userToken, measure);
		if (!created) {
			json.put("status", "400").put("message", "Unable to create");
			return Response.status(400).entity(json.toString()).build();
		}

		// #2 CHECK IF SOME GOALS HAVE BEEN ACHIEVED
		GoalClient goalClient = new GoalClient();
		
		JSONArray goals = goalClient.checkGoals(userToken, new Date());
		if (goals == null) {
			json.put("status", "200")
			.put("message", "")
			.put("achievedGoals", "[]");
			return Response.status(200).entity(json.toString()).build();
		}

		// #3 SEND TWEET IF ACHIEVED
		JSONArray achievedGoals = filterByAchieved(goals);
		if (achievedGoals.length() > 0) sendTweets(userToken);

		// #4 GET A FUNNY MESSAGE
		String message = (achievedGoals.length() > 0) ? RandomJokesClient.getJoke() : MOTIVATIONAL_MESSAGE;

		// #5 RETURN LIST OF ACHIEVED GOALS
		json.put("status", 200);
		json.put("achievedGoals", achievedGoals);
		json.put("message", message);

		return Response.status(200).entity(json.toString()).build();
	}

	/**
	 * 
	 * @param goals
	 * @return
	 */
	private JSONArray filterByAchieved(JSONArray goals) {
		
		JSONArray filtered = new JSONArray();
		JsonParser parser = new JsonParser();
		
		for (int i = 0; i < goals.length(); i++) {
			
			parser.loadJson(goals.get(i).toString());
			
			try {
				String achieved = parser.getElement("achieved");
				if (achieved.equals("1"))
					filtered.put(goals.get(i));

			} catch (Exception e) {
				continue;
			}
		}
		
		return filtered;
	}

	/**
	 * Helper method
	 * @param user
	 * @param expr
	 * @return
	 */
	private String getInfo(JSONObject user, String expr) {
		JsonParser parser = new JsonParser();
		parser.loadJson(user.toString());
		
		try {
			return parser.getElement(expr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * @param userToken
	 */
	private void sendTweets(String userToken) {
		
		UserClient userClient = new UserClient();
		JSONObject user = userClient.getUser(userToken);
		
		String username = getInfo(user, "twitterUsername");
		if (username == "") return;
		
		String tweet = "@" + username + " Good job!! You achieved your goals!";
		try {
			new TwitterClient().sendTweet(tweet);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
