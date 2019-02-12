package main.webapp.Backend;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */

@Stateless
@Path("users")
public class UserResource {
    @Inject
    public UserService userService;

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") int id){
        return userService.getUser(id);
    }

    @POST
    public User saveUser(User user){
        return userService.saveUser(user);
    }
}
