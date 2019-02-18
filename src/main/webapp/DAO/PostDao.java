package main.webapp.DAO;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 15/02/2019.
 */
public interface PostDao {

    boolean createPost(Post post);
    boolean deletePost(Post post);
    boolean sendPost(Post post, User user);
    boolean sendReaction(User user, Post post, String text, boolean isReaction);
    List<Post> getLatestTenPosts(User user);
}
