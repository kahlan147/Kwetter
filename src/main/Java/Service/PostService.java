package Service;

import Classes.Post;
import Classes.User;
import DAO.IPostDao;
import DAO.IUserDao;

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
    @EJB
    private IUserDao userDao;

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

    public Post addUserToPost(long postId, long userId){
        User user = userDao.getUser(userId);
        Post post = postDao.getPost(postId);
        post.setPoster(user);
        return postDao.updatePost(post);
    }

    public Post sendReaction(long postId, long reactionId){
        Post postReactionTo = postDao.getPost(postId);
        Post reaction = postDao.getPost(reactionId);
        return postDao.sendReaction(postReactionTo, reaction);
    }

    public List<Post> getLatestTenPosts(long id){
        User user = userDao.getUser(id);
        return postDao.getLatestTenPosts(user);
    }

    public List<Post> getAllPostsFrom(long id){
        User user = userDao.getUser(id);
        return postDao.getAllPostsFrom(user);
    }

}
