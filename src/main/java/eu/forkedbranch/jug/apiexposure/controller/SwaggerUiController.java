package eu.forkedbranch.jug.apiexposure.controller;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/swagger-ui")
public class SwaggerUiController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getSwaggerUi(
    		@Context UriInfo uriInfo, 
    		@Context ServletContext servletContext) {

        String path = uriInfo.getBaseUri().getPath().toString();
        String contextPath = servletContext.getContextPath();

        URI redirectUrl = uriInfo
                .getBaseUriBuilder()
                .replacePath(contextPath)
                .path("webjars/swagger-ui/3.6.1/index.html")
                .queryParam("url", path + "swagger.json")
                .build();

        return Response
                .status(Status.MOVED_PERMANENTLY)
                .location(redirectUrl)
                .build();
    }
}
