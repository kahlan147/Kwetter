package Service;

import Classes.Post;
import Classes.User;
import DAO.IPostDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */
@Stateless
public class PostService {

    @EJB
    private IPostDao postDao;

    public Post createPost(Post post){
        return postDao.createPost(post);
    }

    public Post getPost(long id){
        return postDao.getPost(id);
    }

    public boolean deletePost(long id){
        Post post = getPost(id);
        return postDao.deletePost(post);
    }

    public Post sendPost(Post post, User user){
        post.setPoster(user);
        return createPost(post);
    }

    public Post sendReaction(Post newPost, Post postReactionTo){
        return postDao.sendReaction(newPost, postReactionTo);
    }

    public List<Post> getLatestTenPosts(User user){
        return postDao.getLatestTenPosts(user);
    }

    public List<Post> getAllPostsFrom(User user){
        return postDao.getAllPostsFrom(user);
    }

}
