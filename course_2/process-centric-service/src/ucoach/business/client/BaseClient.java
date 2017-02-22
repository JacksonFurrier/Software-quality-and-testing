package ucoach.business.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import ucoach.util.*;

public class BaseClient {
	
	protected JsonParser jsonParser = new JsonParser();
	protected String baseUrl = "https://ucoach-business-logic-service.herokuapp.com/business";
	protected transient WebTarget baseTarget;
	protected String mediaType = MediaType.APPLICATION_JSON;

	/**
	 * Class constructor
	 */
	public BaseClient() {
		ClientConfig config = new ClientConfig().register(new JacksonFeature());
		Client client = ClientBuilder.newClient(config);
    baseTarget = client.target(UriBuilder.fromUri(baseUrl).build());
	}

	/**
	 * Perform GET request
	 * @param target
	 * @param userToken
	 * @return
	 */
	protected Response getRequest(WebTarget target, String userToken) {
		
		// Build request
		Builder builder = target.request().accept(mediaType);
		Authorization.authorizeRequest(builder);
		builder.header("User-Authorization", userToken);

		// GET request
		return builder.get();		
	}
	
	/**
	 * Perform POST request
	 * @param target
	 * @param body
	 * @param userToken
	 * @return
	 */
	protected Response postRequest(WebTarget target, String body, String userToken) {

		// Build request
		Builder builder = target.request().accept(mediaType);
		Authorization.authorizeRequest(builder);
		
		if (userToken != null)
			builder.header("User-Authorization", userToken);

		// GET request
		return builder.post(Entity.entity(body, mediaType));
	}

	/**
	 * Parse response status
	 * @param response
	 * @throws Exception
	 */
	protected void parseResponseStatus(Response response) throws Exception {
		// Parse status
		int status = response.getStatus();
		if (!(status == 200 || status == 201 || status == 202 || status == 204)) {
			System.out.println("External Error: response returned " + status);
			System.out.println(response.readEntity(String.class));
			throw new Exception("External Error: response returned " + status);
		}
	}
}
