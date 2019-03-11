package REST;

import Classes.User;
import Service.UserService;

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

    //<editor-fold desc="NO TOUCH! This shit works. Somehow.">

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

    @PUT
    @Path("follow/{idFollower}/{idFollowed}")
    public boolean addFollower(@PathParam("idFollower")long idFollower, @PathParam("idFollowed")long idFollowed) {
        return userService.follow(idFollower, idFollowed);
    }

    @PUT
    @Path("unfollow/{idFollower}/{idFollowed}")
    public boolean removeFollower(@PathParam("idFollower")long idFollower, @PathParam("idFollowed")long idFollowed){
        return userService.unFollow(idFollower, idFollowed);
    }

    @GET
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    //</editor-fold>

}
