package ucoach.auth.restclient;



import java.net.URI;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.client.Invocation.Builder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class UserClient {

	private final transient Client client;
	private final transient WebTarget baseTarget;

	/**
	 * Class constructor
	 * @param serviceUrl
	 */
	public UserClient(URI serviceUrl) {
		ClientConfig config = new ClientConfig().register(new JacksonFeature());
        client = ClientBuilder.newClient(config);
        baseTarget = client.target(serviceUrl);
        
	}
	

	/**
	 * Get person by email in JSON format
	 * @param personId
	 * @return
	 */
	public Response getUserJson(String user) {
		
		WebTarget target = baseTarget
				.path("user")
				.path("email")
				.path(user);
		
		return fetchGetResponse(target, MediaType.APPLICATION_JSON);
	}
	
	

	/**
	 * 
	 * @param target
	 * @param mediaType
	 * @return
	 */
	private Response fetchGetResponse(final WebTarget target, String mediaType) {
		Builder builder = target.request().accept(mediaType);
		Authorization.authorizeRequest(builder);
		return builder.get();
		//return target.request().accept(mediaType).get();
	}
	
	/**
	 * 
	 * @param target
	 * @param mediaType
	 * @return
	 */
	private Response fetchPutResponse(final WebTarget target, String body, String mediaType) {
		return target.request().accept(mediaType).put(Entity.entity(body, mediaType));
	}
	
	/**
	 * 
	 * @param target
	 * @param mediaType
	 * @return
	 */
	private Response fetchPostResponse(final WebTarget target, String body, String mediaType) {
		return target.request().accept(mediaType).post(Entity.entity(body, mediaType));
	}
}