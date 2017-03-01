package ucoach.data.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import ucoach.data.model.Coach;

@WebService(endpointInterface="ucoach.data.ws.CoachInterface",
  serviceName="CoachService")
public class CoachService implements CoachInterface{
  @Resource
  WebServiceContext context;

  @Override
  public Coach getCoach(int coachId) {
    System.out.println("Getting coach " + coachId);
    
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Coach.getCoachById(coachId);
  }

  @Override
  public Coach createCoach(Coach coach) {
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Coach.createCoach(coach);
  }

  @Override
  public Coach updateCoach(Coach coach){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Coach.updateCoach(coach);
  }

  @Override
  public void deleteCoach(int id){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
    } else {
      Coach.deleteCoach(id);
    }
  }
}
