package main.webapp.Backend.pages;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;
import main.webapp.DAO.Database;
import main.webapp.DAO.MockData.MockDaoDatabase;
import main.webapp.DAO.MockData.MockDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class HomepageTest {

    private Database database;

    @Before
    public void setUp() throws Exception {
        database = new MockDaoDatabase();
    }

    @Test
    public void getTweetsBy() {

    }

    @Test
    public void postTweet() {
        User user = database.getUser(9);
        Homepage homepage = new Homepage(user);
        String message = "TestMessage";
        homepage.PostTweet(message);
        Post post = user.getLastPosts(1).get(0);
        assertEquals(post.getMessage(), message);
    }

    @Test
    public void postTweetReaction() {
        User user = database.getUser(9);
        Homepage homepage = new Homepage(user);
        String message = "TestMessage";
        Post respondTo = database.getPost(4);
        homepage.PostTweet(message, respondTo);
        Post post = user.getLastPosts(1).get(0);
        assertEquals(post.getMessage(), message);
        Post lastReaction = post.getReactions().get(post.getReactions().size()-1);
        assertEquals(lastReaction, respondTo);
    }

    @Test
    public void getTweetsOfSelfAndFollowing() {
        User user = database.getUser(0);
        Homepage homepage = new Homepage(user);
        List<Post> allPosts = homepage.GetTweetsOfSelfAndFollowing();
        assertEquals(allPosts.size(), 6);
    }
}