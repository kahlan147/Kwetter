package DAO;

import Backend.Classes.Post;
import Backend.Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 15/02/2019.
 */
public interface IPostDao {

    boolean createPost(Post post);
    boolean deletePost(Post post);
    boolean sendPost(Post post, User user);
    boolean sendReaction(User user, Post post, String text);
    List<Post> getLatestTenPosts(User user);
    List<Post> getAllPosts();
}
