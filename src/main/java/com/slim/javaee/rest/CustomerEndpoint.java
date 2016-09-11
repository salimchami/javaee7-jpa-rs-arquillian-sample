package com.slim.javaee.rest;

import com.slim.javaee.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * @author Salim CHAMI salim.chami@protonmail.com
 */
@Path("/clients")
public class CustomerEndpoint {

	@PersistenceContext(unitName = "poc-persistence-unit")
	private EntityManager em;

	/**
	 *
	 */
	@GET
	@Path("/oneClient")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getOneClient() {
		Customer entity = new Customer(1, "client", new Date());
		em.persist(entity);
		return Response.ok(entity, MediaType.APPLICATION_JSON_TYPE).build();
	}


}
