package ucoach.data.ws;

import ucoach.data.model.GoogleTokens;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface GoogleTokensInterface {
  
  // Get user google tokens by user id
  @WebMethod(operationName="getTokens")
  @WebResult(name="tokens") 
  public GoogleTokens getTokens(@WebParam(name="userId") int userId);

  // Set user google tokens by user id
  @WebMethod(operationName="setTokens")
  @WebResult(name="tokens") 
  public GoogleTokens setTokens(
    @WebParam(name="userId") int userId,
    @WebParam(name="accessToken") String accessToken,
    @WebParam(name="refreshToken") String refreshToken
  );
  
  //Set user google tokens by user id
  @WebMethod(operationName="updateTokens")
	@WebResult(name="tokens") 
	public GoogleTokens updateTokens(
	  @WebParam(name="userId") int userId,
	  @WebParam(name="accessToken") String accessToken
	);
}