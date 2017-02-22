package ucoach.auth.rest.resources;
import ucoach.auth.model.*;
import ucoach.auth.restclient.ClientApp;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/logout/{token}")
public class Logout {
	
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Response logout(@PathParam("token") String token) throws Exception{
		Response r;
		ClientApp ca = new ClientApp();		
		long idPerson;
		try{
			idPerson = AuthToken.getIdPersonByToken(token).getUserid();
			System.out.println(idPerson);
			if(idPerson == 0)
				return Response.status(403).build();
		}catch(Exception e){
			 return Response.status(403).build();
		}
		
		try{
			AuthToken at = AuthToken.getTokenByPerson(idPerson, token);
			AuthToken.removeAuthToken(at);			
			return Response.accepted().build();
		}catch(Exception e){
			return Response.status(500).build();
		}
	}
}
