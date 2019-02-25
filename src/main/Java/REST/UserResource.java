package REST;

import Classes.User;
import Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

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

    @DELETE
    @Path("{id}")
    public boolean removeUser(@PathParam("id") long id){
        return userService.removeUser(id);
    }

    @POST
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    @PUT
    @Path("{id}")
    public boolean updateUser(@PathParam("id")long id, User user){
        return userService.updateUser(id, user);
    }
/*
    @PUT
    public boolean addFollower(User follower, User followed) {
        return userService.follow(follower, followed);
    }*/
}
