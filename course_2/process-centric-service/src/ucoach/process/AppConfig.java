package ucoach.process;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("process")
public class AppConfig extends ResourceConfig {

  public AppConfig () {
    packages("ucoach.process");
  }
}