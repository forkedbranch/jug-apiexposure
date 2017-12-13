package eu.forkedbranch.jug.apiexposure.controller;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import eu.forkedbranch.jug.apiexposure.models.User;
import eu.forkedbranch.jug.apiexposure.services.UserService;

@Path("/users")
public class UsersController {
	private UserService service = UserService.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers()
	{
		return Response.ok().entity(service.getUsers()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(
			@PathParam("id") String id) 
	{
		User user = service.getUser(id);
		if (user != null) {
			return Response.ok().entity(user).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addUser(
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@Context UriInfo info)
	{
		String id = UUID.randomUUID().toString();
		User user = new User(firstName, lastName, id);

		service.addUser(user);

		UriBuilder builder = UriBuilder.fromUri(info.getRequestUri()).path(id);
		return Response.created(builder.build()).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(
			@PathParam("id") String id) 
	{
		User user = service.deleteUser(id);

		if (user != null) {
			return Response.ok().entity(user).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
