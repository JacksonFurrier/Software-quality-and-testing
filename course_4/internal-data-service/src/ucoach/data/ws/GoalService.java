package ucoach.data.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import ucoach.data.model.Goal;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@WebService(endpointInterface="ucoach.data.ws.GoalInterface",
  serviceName="GoalService")
public class GoalService implements GoalInterface {

  @Resource
  WebServiceContext context;

  @Override
  public Goal createGoal(Goal goal, int userId, int hmTypeId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Goal.createGoal(goal, userId, hmTypeId);
  }

  @Override
  public List<Goal> getGoalsFromUser(int userId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Goal.getGoalsFromUser(userId);
  }

  @Override
  public List<Goal> getGoalsFromUserAfterDueDate(int userId, String dueDate){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }

    Date date;    
    try{
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
      date = df.parse(dueDate);
    } catch (ParseException e){
      return null;
    }
    
    return Goal.getGoalsFromUserAfterDueDate(userId, date);
  }

  @Override
  public List<Goal> getGoalsFromUserByFrequencyAndDueDate(int userId, String frequency, String dueDate){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }

    Date date;    
    try{
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
      date = df.parse(dueDate);
    } catch (ParseException e){
      return null;
    }
    
    return Goal.getGoalsFromUserByFrequencyAndDueDate(userId, frequency, date);
  }

  @Override
  public List<Goal> getGoalsFromUserByType(int userId, int hmTypeId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Goal.getGoalsFromUserByHMType(userId, hmTypeId);
  }

  @Override
  public List<Goal> getGoalsFromUserByTypeAndStatus(int userId, int hmTypeId, int achieved){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return Goal.getGoalsFromUserByHMTypeAndAchievedStatus(userId, hmTypeId, achieved);
  }

  @Override
  public void deleteGoal(int goalId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
    } else {
      Goal.deleteGoal(goalId);
    }
  }

  @Override
  public Goal achieveGoal(int goalId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    Goal goal = Goal.getGoalById(goalId);
    return Goal.achieveGoal(goal);
  }
}
