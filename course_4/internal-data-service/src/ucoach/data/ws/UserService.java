package ucoach.data.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import ucoach.data.model.User;

@WebService(endpointInterface="ucoach.data.ws.UserInterface",
  serviceName="UserService")
public class UserService implements UserInterface{
  @Resource
  WebServiceContext context;

  @Override
  public User getUser(int userId) {
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return User.getUserById(userId);
  }

  @Override
  public User getUserByEmail(String email){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }

    return User.getUserByEmail(email);
  }

  @Override
  public User createUser(User user) {
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return User.createUser(user);
  }

  @Override
  public User updateUser(User user){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return User.updateUser(user);
  }

  @Override
  public void deleteUser(int id){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
    } else {
      User.deleteUser(id);
    }
  }
}
