package ucoach.process;

import ucoach.process.AppConfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App
{
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
  {
    String protocol = "http://";
    String portValue = "5001";
    if (String.valueOf(System.getenv("PORT")) != "null"){
      portValue = String.valueOf(System.getenv("PORT"));
    }
    String port = ":" + portValue + "/";
    String hostname = InetAddress.getLocalHost().getHostAddress();
    if (hostname.equals("127.0.0.1")) {
      hostname = "localhost";
    }

    URI BASE_URI = new URI(protocol + hostname + port + "process/");

    JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
    System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");
  }

  public static ResourceConfig createApp() {
    System.out.println("Starting services...");
    return new AppConfig();
  }
}