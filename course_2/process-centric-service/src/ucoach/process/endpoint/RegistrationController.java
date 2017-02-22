package ucoach.process.endpoint;

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

import org.json.JSONObject;

import ucoach.authentication.client.Login;
import ucoach.business.client.UserClient;
import ucoach.external.TwitterClient;
import ucoach.util.Authorization;

@Path("/register")
public class RegistrationController {

	@POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response afterLoginProcedure(@Context HttpHeaders headers, String userData) {
		// Build JSON response object
		JSONObject json = new JSONObject();
			
		if (!Authorization.validateRequest(headers)) {
			json.put("status", "401").put("message", "Unauthorize");
			return Response.status(401).entity(json.toString()).build();
		}

		// #1 REGISTER NEW USER
		UserClient userClient = new UserClient();
		JSONObject user = userClient.newUser(userData);
		if (user == null) {
			System.out.println("Unable to create user");
			json.put("status", "400").put("message", "Unable to create user");
			return Response.status(400).entity(json.toString()).build();
		}

		json.put("status", 200);

		// #2 LOGIN THE USER AND GET TOKEN
		String username = user.getString("email");
		String password = user.getString("password");
		String twitterUser = user.getString("twitterUsername");
		
		Login login = new Login(username, password);
		String token = login.getToken();
		if (token != null) json.put("token", token);
		
		// #3 SEND WELCOME TWEET
		sendWelcomeTweet(twitterUser);
		
		// #4 RETURN TOKEN
		json.put("status", 200);

		return Response.status(200).entity(json.toString()).build();
	}
	
	/**
	 * 
	 * @param twitterUser
	 */
	private void sendWelcomeTweet(String twitterUser) {
		if (twitterUser == null || twitterUser == "") return;
		
		String tweet = "@" + twitterUser + " Welcome to uCoach!";
		try {
			new TwitterClient().sendTweet(tweet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
