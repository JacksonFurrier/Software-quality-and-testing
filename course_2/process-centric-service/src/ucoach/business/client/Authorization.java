package ucoach.business.client;

import javax.ws.rs.client.Invocation.Builder;

public class Authorization {

	private static final String AUTHORIZATION_KEY = "default_authorization_key";
	
	/**
	 * Authorize request with authentication key
	 * @param provider
	 */
	public static void authorizeRequest(Builder builder) {
		
		// Get valid authorization key from Environment
    String validAuthKey = AUTHORIZATION_KEY;
    if (String.valueOf(System.getenv("BUSINESS_AUTH_KEY")) != "null"){
    	validAuthKey = String.valueOf(System.getenv("BUSINESS_AUTH_KEY"));
    }

    builder.header("Authorization", validAuthKey);
	}
}
