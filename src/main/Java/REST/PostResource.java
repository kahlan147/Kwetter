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

    @PUT
    @Path("{postId}/add/{userId}")
    public Post addUserToPost(@PathParam("postId") long postId, @PathParam("userId") long userId){
        return postService.addUserToPost(postId,userId);
    }

    @PUT
    @Path("{postId}/reaction/{reactionId}")
    public Post sendReaction(@PathParam("postId") long postId, @PathParam("reactionId") long reactionId){
        return postService.sendReaction(postId, reactionId);
    }

    @GET
    @Path("{id}/last10posts")
    public List<Post> getLatestTenPosts(@PathParam("id") long id){
        return postService.getLatestTenPosts(id);
    }

    @GET
    @Path("{id}/allposts")
    public List<Post> getAllPostsFrom(@PathParam("id") long id){
        return postService.getAllPostsFrom(id);
    }
}
