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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@Path("/users")
public class UsersController {
	private UserService service = UserService.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Get all users",
			notes = "Some more elaborate text on getting all users",
			response = User.class,
			responseContainer = "List"
			)
	public Response getUsers()
	{
		return Response.ok().entity(service.getUsers()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Get user by ID",
			notes = "Some more elaborate text on getting a user by ID",
			response = User.class
			)
	@ApiResponses(
		@ApiResponse(
				code = 404,
				message = "User resource not found"
				)
		)
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
	@ApiOperation(
			value = "Add user",
			notes = "Some more elaborate text on adding a user"
			)
	@ApiResponses(
			@ApiResponse(
					code = 201, 
					message = "User successfuly created"
					)
			)
	public Response addUser(
			@ApiParam(required = true, value="First name of a user")
			@FormParam("firstName") String firstName,
			@ApiParam(required = true, value="Last name of a user")
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
	@ApiOperation(
			value = "Delete user by ID",
			notes = "Some more elaborate text on deleting a user by ID",
			response = User.class
			)
	@ApiResponses(
		@ApiResponse(
				code = 404,
				message = "User resource not found"
				)
		)
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
