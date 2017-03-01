package ucoach.data.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import ucoach.data.model.User;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface UserInterface {

  @WebMethod(operationName="getUser")
  @WebResult(name="user") 
  public User getUser(@WebParam(name="userId") int userId);

  @WebMethod(operationName="getUserByEmail")
  @WebResult(name="userByEmail") 
  public User getUserByEmail(@WebParam(name="email") String email);

  @WebMethod(operationName="createUser")
  @WebResult(name="createdUser")
  public User createUser(@WebParam(name="user") User user);

  @WebMethod(operationName="updateUser")
  @WebResult(name="updateUser")
  public User updateUser(@WebParam(name="user") User user);

  @WebMethod(operationName="deleteUser")
  public void deleteUser(@WebParam(name="userId") int id);
}
