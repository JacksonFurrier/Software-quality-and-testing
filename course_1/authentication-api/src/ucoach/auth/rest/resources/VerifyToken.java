package ucoach.auth.rest.resources;
import ucoach.auth.model.*;
import ucoach.auth.restclient.ClientApp;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/token/verify/{token}")
public class VerifyToken {
	
	/*
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public RestToken getUserFromToken(){
		return null;
	}*/
	
	
	
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getPerson(@PathParam("token") String token) {
		long userID;
		try{
        	userID = AuthToken.getIdPersonByToken(token).getUserid();
        }catch(Exception e){
        	return Response.status(404).build();
        }        
		RestToken rt = new RestToken();
        rt.setToken(token);
        rt.setId(""+userID);
		return Response.accepted(rt).build();
    }
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public AuthToken login(RestToken rt) throws Exception{
		Response r;
		ClientApp ca = new ClientApp();
		int idPerson = 0;
		try{
			idPerson = Integer.parseInt(rt.getId());
		}catch(Exception e){
			 r =  Response.noContent().build();
			 return null;
		}
		
		try{
			AuthToken at = AuthToken.getTokenByPerson(idPerson, rt.getToken());
			AuthToken.removeAuthToken(at);			
			return at;
		}catch(Exception e){
			
		}
		
		
		return null;
	}
}
