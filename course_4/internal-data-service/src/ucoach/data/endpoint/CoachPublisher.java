package ucoach.data.endpoint;

import ucoach.data.ws.CoachService;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

public class CoachPublisher {

  public static String ENDPOINT_URL = "/ws/coach";

  /**
   * Build endpoint url
   * @return
   * @throws UnknownHostException 
   */
  public static String getEndpointURL() throws UnknownHostException {

  	String portValue = "6900";
    if (String.valueOf(System.getenv("PORT")) != "null"){
        portValue = String.valueOf(System.getenv("PORT"));
    }
    String port = ":" + portValue;

    String hostname = InetAddress.getLocalHost().getHostAddress();
    if (hostname.equals("127.0.0.1")) {
        hostname = "localhost";
    }

    return "http://" + hostname + port + ENDPOINT_URL;
  }

  /**
   * Publish endpoint
   * @throws UnknownHostException
   */
  public static void publish() throws UnknownHostException {
    
  	System.out.println("Starting CoachService");

    // Start Coach service
    String endpointUrl = getEndpointURL();
    System.out.println("--> Published at " + endpointUrl);
    Endpoint.publish(endpointUrl, new CoachService());
  }

  public static void main(String[] args) throws UnknownHostException {
    publish();
  }
}