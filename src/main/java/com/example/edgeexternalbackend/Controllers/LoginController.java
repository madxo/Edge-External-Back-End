package com.example.edgeexternalbackend.Controllers;

import com.example.edgeexternalbackend.Constants.LoginConstants;
import com.example.edgeexternalbackend.Modal.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;



@Component
@Path("/login")
public class LoginController {

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response Login(@QueryParam("email") String email, @QueryParam("password") String password) {
        User user = LoginConstants.USER_MAP.get(email);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User does not exist").build();
        }
        if (!user.getPassword().equals(password)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect credentials provided").build();
        }
        return Response.ok().entity(user).build();
    }
}
