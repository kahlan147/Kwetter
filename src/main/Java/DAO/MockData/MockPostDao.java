package DAO.MockData;

import Classes.Post;
import Classes.User;
import DAO.IPostDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class MockPostDao implements IPostDao {

    private List<Post> posts;

    public MockPostDao(){
        posts = new ArrayList<>();
    }

    @Override
    public Post createPost(Post post) {
        long id = posts.size();
        posts.add(post);
        post.setId(id);
        return post;
    }

    @Override
    public Post getPost(long id) {
        return posts.get((int)id);
    }

    @Override
    public boolean deletePost(Post post) {
        posts.remove(post);
        return true;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post sendReaction(Post post, Post reaction) {
        reaction.setIsReaction(true);
        post.addToReactions(reaction);
        return post;
    }

    @Override
    public List<Post> getLatestTenPosts(User user) {
        return user.getLastPosts(10);
    }

    @Override
    public List<Post> getAllPostsFrom(User user) {
        return user.getAllPosts();
    }

    @Override
    public List<Post> getAllPosts(){
        return posts;
    }
}
