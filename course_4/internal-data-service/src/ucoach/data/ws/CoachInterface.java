package ucoach.data.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import ucoach.data.model.Coach;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface CoachInterface {

  @WebMethod(operationName="getCoach")
  @WebResult(name="coach") 
  public Coach getCoach(@WebParam(name="coachId") int coachId);

  @WebMethod(operationName="createCoach")
  @WebResult(name="createdCoach")
  public Coach createCoach(@WebParam(name="coach") Coach coach);

  @WebMethod(operationName="updateCoach")
  @WebResult(name="updateCoach")
  public Coach updateCoach(@WebParam(name="coach") Coach coach);

  @WebMethod(operationName="deleteCoach")
  public void deleteCoach(@WebParam(name="coachId") int id);
}
