package DAO;

import Classes.Post;
import Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 15/02/2019.
 */
public interface IPostDao {

    Post createPost(Post post);
    Post getPost(long id);
    boolean deletePost(Post post);
    Post sendReaction(Post newPost, Post postToReactTo);
    List<Post> getAllPosts();
    List<Post> getLatestTenPosts(User user);
    List<Post> getAllPostsFrom(User user);
}
