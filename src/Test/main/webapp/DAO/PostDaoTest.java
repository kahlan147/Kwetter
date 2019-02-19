package main.webapp.DAO;

import Backend.Classes.Post;
import Backend.Classes.User;
import DAO.PostDao;
import DAO.PostDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 18/02/2019.
 */
public class PostDaoTest {

    private PostDao postDao;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        postDao = new PostDaoImpl();
        user1 = new User("Testboy", "test");
        user2 = new User("Testgirl", "test");
    }

    @Test
    public void createPost(){
        Post post = new Post("new Post!", user1);
        postDao.createPost(post);
        assertEquals(postDao.getAllPosts().size(), 1);
    }

    @Test
    public void deletePost(){
        Post post = new Post("new post!", user1);
        postDao.createPost(post);
        assertEquals(postDao.getAllPosts().size(), 1);
        postDao.deletePost(post);
        assertEquals(postDao.getAllPosts().size(),0);
    }

    @Test
    public void sendPost(){
        Post post = new Post("new post!");
        postDao.sendPost(post, user2);
        assertEquals(post.getPoster(), user2);
        assertNotEquals(post.getPoster(), user1);
        assertNotEquals(post.getPoster(), null);
    }

    @Test
    public void sendReaction(){
        Post post = new Post("new post!");
        postDao.sendReaction(user1, post, "reaction!");
        Post reactionPost = post.getReactions().get(0);
        assertNotEquals(reactionPost, null);
        assertEquals(reactionPost.getPoster(), user1);
        assertEquals(reactionPost.getMessage(), "reaction!");
        assertTrue(reactionPost.getIsReaction());
    }

    @Test
    public void getLatestTenPosts(){
        Post post = new Post("new post!");
        postDao.sendPost(post, user1);
        Post post1 = new Post("new post!");
        postDao.sendPost(post1, user1);
        Post post2 = new Post("new post!");
        postDao.sendPost(post2, user1);
        Post post3 = new Post("new post!");
        postDao.sendPost(post3, user1);

        List<Post> posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 4);

        Post post4 = new Post("new post!");
        postDao.sendPost(post4, user1);
        Post post5 = new Post("new post!");
        postDao.sendPost(post5, user1);
        Post post6 = new Post("new post!");
        postDao.sendPost(post6, user1);
        Post post7 = new Post("new post!");
        postDao.sendPost(post7, user1);
        Post post8 = new Post("new post!");
        postDao.sendPost(post8, user1);
        Post post9 = new Post("new post!");
        postDao.sendPost(post9, user1);

        posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 10);

        Post post10 = new Post("new post!");
        postDao.sendPost(post10, user1);

        posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 10);
    }
}