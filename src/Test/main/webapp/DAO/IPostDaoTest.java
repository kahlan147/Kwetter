package main.webapp.DAO;

import Classes.Post;
import Classes.User;
import DAO.IPostDao;
import DAO.MockData.MockPostDao;
import DAO.PostDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 18/02/2019.
 */
public class IPostDaoTest {

    private IPostDao postDao;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        postDao = new MockPostDao();
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
    public void sendReaction(){
        Post post = new Post("new post!", user2);
        postDao.createPost(post);
        Post secondPost = new Post("reaction!", user1);
        postDao.createPost(post);
        postDao.sendReaction(post, secondPost);
        Post reactionPost = post.getReactions().get(0);
        assertNotEquals(reactionPost, null);
        assertEquals(reactionPost.getPoster(), user1);
        assertEquals(reactionPost.getMessage(), "reaction!");
        assertTrue(reactionPost.getIsReaction());
    }

    @Test
    public void getLatestTenPosts(){
        Post post = new Post("new post!", user1);
        postDao.createPost(post);
        Post post1 = new Post("new post!", user1);
        postDao.createPost(post1);
        Post post2 = new Post("new post!", user1);
        postDao.createPost(post2);
        Post post3 = new Post("new post!", user1);
        postDao.createPost(post3);

        List<Post> posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 4);

        Post post4 = new Post("new post!", user1);
        postDao.createPost(post4);
        Post post5 = new Post("new post!", user1);
        postDao.createPost(post5);
        Post post6 = new Post("new post!", user1);
        postDao.createPost(post6);
        Post post7 = new Post("new post!", user1);
        postDao.createPost(post7);
        Post post8 = new Post("new post!", user1);
        postDao.createPost(post8);
        Post post9 = new Post("new post!", user1);
        postDao.createPost(post9);

        posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 10);

        Post post10 = new Post("new post!", user1);
        postDao.createPost(post10);

        posts = postDao.getLatestTenPosts(user1);
        assertEquals(posts.size(), 10);
    }
}