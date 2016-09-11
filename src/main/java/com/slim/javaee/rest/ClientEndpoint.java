package com.slim.javaee.rest;

import com.slim.javaee.model.Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * 
 */
@Path("/clients")
public class ClientEndpoint {

	/**
	 *
	 */
	@GET
	@Path("/oneClient")
	@Produces("application/json")
	public Response findById() {
		Client entity = new Client(1, "client", new Date());
		return Response.ok(entity).build();
	}


}
