package ucoach.auth.rest.resources;
import ucoach.auth.model.*;
import ucoach.auth.restclient.ClientApp;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/login")
public class TokenResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })	
	public Response login(Login l) throws Exception{
		
		ClientApp ca = new ClientApp();		
		return ca.login(l.getUsername(), l.getPassword());
	}
}
