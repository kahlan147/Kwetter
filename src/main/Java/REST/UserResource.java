package REST;

import Classes.User;
import JWTFilter.JWTTokenNeeded;
import Service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.primefaces.json.JSONObject;

import javax.crypto.KeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */

@Stateless
@Path("users")
public class UserResource {

    @Inject
    public UserService userService;

    @GET
    @Path("login/{name}/{password}")
    public User logIn(@PathParam("name") String name, @PathParam("password") String password) {
            return userService.logIn(name, password);
    }

    @POST
    @Path("loginAuth/{name}/{password}")
    public Response logInAuth(@PathParam("name") String name, @PathParam("password") String password){
        try{
            userService.logIn(name, password);

            String token = issueToken("accepted");
            // Return the token on the response
            JSONObject json = new JSONObject();
            json.put("access_token","Bearer" + token);
            return Response.ok(json.toString()).header(AUTHORIZATION, "Bearer " + token).build();
        }
        catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String login) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(login);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(new Date().toInstant().plusSeconds(900L).toEpochMilli()));
        builder.signWith(key);

        return builder.compact();
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

    @GET
    @Path("{id}/followers")
    public List<User> getAllFollowers(@PathParam("id")long id){return userService.getAllFollowers(id);}

}
