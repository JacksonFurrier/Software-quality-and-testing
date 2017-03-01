package ucoach.data.endpoint;

import java.net.UnknownHostException;

public class AllPublisher {

  public static void main(String[] args) throws UnknownHostException {
  	
  	// Publish all endpoints
  	GoogleTokensPublisher.publish();
  	UserPublisher.publish();
    CoachPublisher.publish();
    HealthMeasurePublisher.publish();
    GoalPublisher.publish();
  }
}