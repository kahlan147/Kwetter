package Backend.REST;

import Backend.Classes.User;
import Backend.Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

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
    public User getUser(@PathParam("id") long id){
        return userService.getUser(id);
    }

    @POST
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    @PUT
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }
/*
    @PUT
    public boolean addFollower(User follower, User followed){
        return userService.follow(follower,followed);
    }*/
}
