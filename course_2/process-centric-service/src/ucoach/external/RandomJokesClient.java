package ucoach.external;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import ucoach.util.JsonParser;

public class RandomJokesClient {
	
	/**
	 * Get random joke message
	 * @return
	 */
	public static String getJoke() {
		
		JsonParser jsonParser = new JsonParser();
		WebTarget baseTarget = getWebTarget();
		WebTarget target = baseTarget.path("random");
		
		try {
			Response r = fetchGetResponse(target, "application/json");
			
			jsonParser.loadJson(r.readEntity(String.class));
			return jsonParser.getElement("joke") ; 

		} catch(Exception ex) {
			return null;
		}
	}

	/**
	 * Get web target
	 * @return
	 */
	public static WebTarget getWebTarget(){
		ClientConfig config = new ClientConfig().register(new JacksonFeature());
		Client client = ClientBuilder.newClient(config);		
		WebTarget baseTarget = client.target(getBaseURI() );
		return baseTarget;
	}
	
	/**
	 * 
	 * @param target
	 * @param mediaType
	 * @return
	 */
	public static Response fetchGetResponse(final WebTarget target, String mediaType) {		
		// Build request
		Builder builder = target.request().accept(mediaType);
		// GET request
		return builder.get();
	}
	
	/**
	 * 
	 * @return
	 */
	private static URI getBaseURI() {
    return UriBuilder.fromUri("http://tambal.azurewebsites.net/joke/").build();
  }
}
