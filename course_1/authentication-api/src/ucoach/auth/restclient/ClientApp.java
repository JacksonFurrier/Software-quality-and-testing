package ucoach.auth.restclient;

import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.w3c.dom.NodeList;

import ucoach.auth.model.AuthToken;
import ucoach.auth.restclient.util.JsonParser;

public class ClientApp {

	static PrintWriter xmlLogWriter;
	static PrintWriter jsonLogWriter;

	
	static JsonParser jsonParser = new JsonParser();
	
	static Random randomGenerator = new Random();

	static int reqNumber;
	static String method;
	static String url;
	static String content;
	static String accept;
	static Response response;
	static String bodyXml;
	static String bodyJson;
	static boolean isValid;
	
	public static ArrayList<Integer> successStatus = new ArrayList<Integer>();

	public static Class<String> stringClass = String.class;

	public static UserClient personClient = new UserClient(getBaseURI());
	

	public Response login(String userName, String criptedPassword) throws Exception{
		Response r;
		accept = "application/json";
		try{
			response = personClient.getUserJson(userName);
			if (response.getStatus()==404)
				return Response.status(401).build();
			bodyJson = fetchObjectFromResponse(response, stringClass);
			System.out.println(bodyJson);
			jsonParser.loadJson(bodyJson);
			long personId = Long.parseLong(jsonParser.getElement("id"));
			String dbPassword = jsonParser.getElement("password");
			AuthToken at = new AuthToken();
			System.out.println(personId);
			if(dbPassword.equals(criptedPassword) ){
				at.setUserid(personId);
				at.setCreated(new Date());
				at.generateNewRandonToken();
				at = AuthToken.saveToken(at);
				r = Response.accepted(at).build();
				
			}else{
				r = Response.status(401).build();
			}
		}catch(Exception e){
			r = Response.status(501).build();
		}
		return r;
		
		
		
	}

    /**
     * 
     * @param response
     * @param reqNumber
     * @param method
     * @param url
     * @param accept
     * @param content
     */
    private static void printResponseOutput(Response response, int reqNumber, String method,
    		String url, String accept, boolean isValid, String content, String body) {

    	// Build log message
    	String message = "============================================================"
    		+ "\nRequest #" + reqNumber + ": " + method + " " + url;
		if (accept != "") message += " Accept: " + accept;
		if (content != "") message += " Content-Type: " + content;
		
		int status = response.getStatus();
		message += "\n=> Result: " + isResponseValid(status, isValid);
		message += "\n=> HTTP Status: " + response.getStatus();
		message += "\n" + body;
		
		// Print message and save to correct log files
		System.out.println(message);
		
		switch (accept) {
			case "application/xml":
				xmlLogWriter.println(message);
				break;
				
			case "application/json":
				jsonLogWriter.println(message);
				break;

			default:
				xmlLogWriter.println(message);
				jsonLogWriter.println(message);
		}		
    }

    /**
     * 
     * @param response
     * @param returnType
     * @return
     */
    private static <T> T fetchObjectFromResponse(Response response, final Class<T> returnType) {
		return response.readEntity(returnType);
	}

	/**
	 * Helper method to return response validness
	 * according to status and extra requirements
	 *
	 * @param response
	 * @return
	 */
	private static String isResponseValid(int status, boolean isValid) {
		return (isValid & successStatus.contains(status)) ? "OK" : "ERROR";
	}

	/**
	 * 
	 * @return
	 */
    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://ucoach-data-service.herokuapp.com/data/").build();
    }
}