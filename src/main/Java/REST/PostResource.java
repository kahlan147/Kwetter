package REST;

import Classes.Post;
import Classes.User;
import Service.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */
@Stateless
@Path("posts")
public class PostResource {

    @Inject
    public PostService postService;

    @POST
    public Post createPost(Post post){
        return postService.createPost(post);
    }

    @GET
    @Path("{id}")
    public Post getPost(@PathParam("id") long id){
        return postService.getPost(id);
    }

    @DELETE
    @Path("{id}")
    public boolean deletePost(@PathParam("id")long id){
        return postService.deletePost(id);
    }

    @POST
    public Post sendPost(Post post, User user){
        return postService.sendPost(post,user);
    }

    @PUT
    public Post sendReaction(Post newPost, Post postReactionTo){
        return postService.sendReaction(newPost, postReactionTo);
    }

    @GET
    @Path("/last10posts/")
    public List<Post> getLatestTenPosts(User user){
        return postService.getLatestTenPosts(user);
    }

    @GET
    @Path("/allposts/")
    public List<Post> getAllPostsFrom(User user){
        return postService.getAllPostsFrom(user);
    }
}
