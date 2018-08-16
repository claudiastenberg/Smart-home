package se.claudiastenberg.MyHome.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.claudiastenberg.MyHome.model.User;
import se.claudiastenberg.MyHome.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("users")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XHTML_XML})
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService service;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public UserResource(UserService service) {
        this.service = service;
    }
    @POST
    public Response createUser(User user){
        service.createUser(user);
        return Response.ok().build();
    }
    @GET
    @Path("{accountName}/{controller_id}/{status}")
    public Response getUser(@PathParam("accountName") Long accountName, @PathParam("controller_id") Integer controller_id, @PathParam("status") Integer status) throws Exception {
        return Response.ok(service.getIdFromUser(accountName, controller_id, status)).build();
    }
}
