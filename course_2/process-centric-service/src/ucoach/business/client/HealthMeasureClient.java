package ucoach.business.client;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class HealthMeasureClient extends BaseClient {

	public HealthMeasureClient() {
		// Call parent constructor
		super();
		baseTarget = baseTarget.path("user/measure");
	}

	/**
	 * 
	 * @param userToken
	 * @param measure
	 * @return
	 */
	public boolean newHealthMeasure(String userToken, String measure) {

		// Build target URL and send GET request
		Response response = postRequest(baseTarget, measure, userToken);
		
		try {
			parseResponseStatus(response);
		} catch (Exception e) { return false; }
		
		return true;
	}
}
