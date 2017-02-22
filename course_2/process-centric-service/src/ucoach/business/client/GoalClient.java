package ucoach.business.client;

import java.util.Date;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import ucoach.util.*;

public class GoalClient extends BaseClient {

	public GoalClient() {
		// Call parent constructor
		super();
		baseTarget = baseTarget.path("/user/goal-update");
	}

	/**
	 * 
	 * @param userToken
	 * @param date
	 * @return
	 */
	public JSONArray checkGoals(String userToken, Date date) {

		// Build target URL and send GET request
		baseTarget.queryParam("dateFrom", DatePatterns.dateFormater(date));
		Response response = getRequest(baseTarget, userToken);

		// Parse response
		try {
			
			parseResponseStatus(response);
			return new JSONArray(response.readEntity(String.class));

		} catch (Exception e) {
			System.out.println("External error: " + e.getMessage());
			return null;
		}		
	}
	
	/**
	 * 
	 * @param userToken
	 * @param date
	 * @return
	 */
	public JSONArray cloneGoals(String userToken) {

		// Build target URL and send GET request
		baseTarget.path("clone");
		Response response = getRequest(baseTarget, userToken);

		// Parse response
		try {
			parseResponseStatus(response);
			return new JSONArray(response.readEntity(String.class));

		} catch (Exception e) {
			System.out.println("External error: " + e.getMessage());
			return null;
		}		
	}
	
	
}
