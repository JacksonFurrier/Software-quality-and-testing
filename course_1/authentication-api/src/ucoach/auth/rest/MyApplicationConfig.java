package ucoach.auth.rest;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("auth")
public class MyApplicationConfig extends ResourceConfig {
	public MyApplicationConfig () {
        packages("ucoach.auth.rest");
    }
}