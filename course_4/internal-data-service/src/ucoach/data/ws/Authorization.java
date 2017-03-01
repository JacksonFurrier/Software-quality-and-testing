package ucoach.data.ws;

import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class Authorization {

	private static final String AUTHORIZATION_KEY = "default_authorization_key";
	
	/**
	 * Method to authenticate key
	 *
	 * @param context
	 * @return
	 */
	public static boolean validateRequest(WebServiceContext context) {
		
		MessageContext message = context.getMessageContext();
		
		// Get detail from request headers
		Map headers = (Map) message.get(MessageContext.HTTP_REQUEST_HEADERS);
		List keyList = (List) headers.get("Authorization");

		String authKey = "";
		if(keyList != null){
			authKey = keyList.get(0).toString();
		}

		// Get valid authentication key from Environment
		String validAuthKey = AUTHORIZATION_KEY;
		if (String.valueOf(System.getenv("INTERNAL_DATA_AUTH_KEY")) != "null"){
			validAuthKey = String.valueOf(System.getenv("INTERNAL_DATA_AUTH_KEY"));
		}

		if (authKey.equals(validAuthKey)){
			return true;
		}

		return false;
	}
}
