package ucoach.util;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

public class Authorization {

	private static final String AUTHORIZATION_KEY = "default_authorization_key";
	
	/**
	 * Method to authenticate key from Authorization header
	 *
	 * @param headers
	 * @return
	 */
	public static boolean validateRequest(HttpHeaders headers) {
		
		if (headers == null) return false;
		
		// Get detail from request headers
		List<String> keyList = headers.getRequestHeader("Authorization");
		String authKey = "";
		if(keyList != null){
			authKey = keyList.get(0).toString();
		}

		String validAuthKey = getAuthorizationKey();

		if (authKey.equals(validAuthKey)){
			return true;
		}

		return false;
	}

	/**
	 * Method to return authorization key
	 * @return
	 */
	private static String getAuthorizationKey() {

		// Get valid authentication key from Environment
		String validAuthKey = AUTHORIZATION_KEY;
		if (String.valueOf(System.getenv("PROCESS_AUTH_KEY")) != "null"){
			validAuthKey = String.valueOf(System.getenv("PROCESS_AUTH_KEY"));
		}

		return validAuthKey;
	}
}
