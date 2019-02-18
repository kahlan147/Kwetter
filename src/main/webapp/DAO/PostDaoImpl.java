package main.webapp.DAO;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class PostDaoImpl implements PostDao {

    private List<Post> posts;

    public PostDaoImpl(){
        posts = new ArrayList<>();
    }

    @Override
    public boolean createPost(Post post) {
        posts.add(post);
        return true;
    }

    @Override
    public boolean deletePost(Post post) {
        posts.remove(post);
        return true;
    }

    @Override
    public boolean sendPost(Post post, User user) {
        post.setPoster(user);
        return createPost(post);
    }

    @Override
    public boolean sendReaction(User user, Post post, String text, boolean isReaction) {
        Post newPost = new Post(text, user);
        newPost.setIsReaction(isReaction);
        post.addToReactions(newPost);
        return true;
    }

    @Override
    public List<Post> getLatestTenPosts(User user) {
        return user.getLastPosts(10);
    }
}
