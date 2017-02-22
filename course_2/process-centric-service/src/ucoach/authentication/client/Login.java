package ucoach.authentication.client;

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
import org.json.JSONObject;

import ucoach.util.JsonParser;

public class Login {

	private String user;
	private String password;

	/**
	 * Constructor
	 * @param user
	 * @param password
	 */
	public Login(String user, String password) {
		this.user = user;
		this.password = password;		
	}
	
	/**
	 * Perform login and get user token
	 * @return
	 */
	public String getToken() {

		JSONObject loginJson = new JSONObject();
		JsonParser jsonParser = new JsonParser();
		loginJson.put("username", this.user);
		loginJson.put("password", this.password);
		System.out.println(loginJson);
		WebTarget baseTarget = getWebTarget();
		WebTarget target = baseTarget.path("login");
		try{
			
			Response r = fetchPostResponse(target, "application/json", loginJson);	
			jsonParser.loadJson(r.readEntity(String.class));
			return jsonParser.getElement("token") ; 
			
		} catch(Exception ex) {
			return null;
		}
	}
	
	/**
	 * Helper method to get base web target
	 * @return
	 */
	public static WebTarget getWebTarget(){
		ClientConfig config = new ClientConfig().register(new JacksonFeature());
		Client client = ClientBuilder.newClient(config);		
		WebTarget baseTarget = client.target(getBaseURI() );
		return baseTarget;
	}
	
	/**
	 * Helper method to fetch response
	 * @param target
	 * @param mediaType
	 * @param json
	 * @return
	 */
	public static Response fetchPostResponse(final WebTarget target, String mediaType, JSONObject json) {
		
		// Build request
		Builder builder = target.request().accept(mediaType);
		// GET request
		return builder.post(Entity.entity(json.toString(), mediaType));
	}
	
	/**
	 * Get base URI
	 * @return
	 */
	private static URI getBaseURI() {
    return UriBuilder.fromUri("https://ucoach-authentication-api.herokuapp.com/auth/").build();
  }
}
