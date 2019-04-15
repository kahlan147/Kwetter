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
    @Path("login/{name}/{password}")
    public User logIn(@PathParam("name") String name, @PathParam("password") String password){
        return userService.logIn(name, password);
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") long id){
        return userService.getUser(id);
    }

    @GET
    @Path("byName/{name}")
    public List<User> getUsersByName(@PathParam("name") String name){
        return userService.getUsersByName(name);
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
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    @PUT
    @Path("{idFollower}/follow/{idFollowed}")
    public boolean addFollower(@PathParam("idFollower")long idFollower, @PathParam("idFollowed")long idFollowed) {
        return userService.follow(idFollower, idFollowed);
    }

    @PUT
    @Path("{idFollower}/unfollow/{idFollowed}")
    public boolean removeFollower(@PathParam("idFollower")long idFollower, @PathParam("idFollowed")long idFollowed){
        return userService.unFollow(idFollower, idFollowed);
    }

    @GET
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GET
    @Path("{id}/followings")
    public List<User> getAllFollowing(@PathParam("id")long id){return userService.getAllFollowing(id);}
    //</editor-fold>

    @GET
    @Path("{id}/followers")
    public List<User> getAllFollowers(@PathParam("id")long id){return userService.getAllFollowers(id);}

}
