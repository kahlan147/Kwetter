package DAO;

import Classes.Post;
import Classes.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Niels Verheijen on 26/02/2019.
 */
@Stateless
public class PostDao implements IPostDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Post createPost(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Post getPost(long id) {
        return em.find(Post.class, id);
    }

    @Override
    public boolean deletePost(Post post) {
        em.remove(post);
        return true;
    }

    @Override
    public Post sendReaction(Post postToReactTo, Post reaction) {
        postToReactTo.addToReactions(reaction);
        em.merge(postToReactTo);
        return postToReactTo;
    }
    @Override
    public Post updatePost(Post post){
        em.merge(post);
        return post;
    }

    @Override
    public List<Post> getAllPosts(){
        return em.createNamedQuery("post.all",Post.class).getResultList();
    }

    @Override
    public List<Post> getLatestTenPosts(User user) {
        return user.getLastPosts(10);
    }

    @Override
    public List<Post> getAllPostsFrom(User user) {
        return user.getAllPosts();
    }
}
