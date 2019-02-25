package main.webapp.Backend.pages;

import Classes.Post;
import Classes.User;
import pages.ProfilePage;
import DAO.IDatabase;
import DAO.MockData.MockDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class ProfilePageTest {

    IDatabase database;

    @Before
    public void setUp() throws Exception {
        database = new MockDatabase();
    }

    @Test
    public void changeNameWithAllowance() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        profilePage.ChangeName("Harry");
        assertEquals(user1.getName(), "Harry");

    }

    @Test
    public void changeNameWithoutAllowance(){
        User user1 = database.getUser(0);
        User user2 = database.getUser(1);
        ProfilePage profilePage = new ProfilePage(user1, user2);
        profilePage.ChangeName("Harry");
        assertNotEquals(user1.getName(), "Harry");
    }

    @Test
    public void changeBioWithAllowance() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        profilePage.ChangeBio("New bio");
        assertEquals(user1.getBio(), "New bio");
    }

    @Test
    public void changeBioWithoutAllowance() {
        User user1 = database.getUser(0);
        User user2 = database.getUser(1);
        ProfilePage profilePage = new ProfilePage(user1, user2);
        profilePage.ChangeBio("New bio");
        assertNotEquals(user1.getBio(), "New bio");
    }

    @Test
    public void changeWebsiteWithAllowance() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        profilePage.ChangeWebsite("www.google.com");
        assertEquals(user1.getWebsite(), "www.google.com");
    }

    @Test
    public void changeWebsiteWithoutAllowance() {
        User user1 = database.getUser(0);
        User user2 = database.getUser(1);
        ProfilePage profilePage = new ProfilePage(user1, user2);
        profilePage.ChangeWebsite("www.google.com");
        assertNotEquals(user1.getWebsite(), "www.google.com");
    }

    @Test
    public void changeLocationWithAllowance() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        profilePage.ChangeWebsite("www.google.com");
        assertEquals(user1.getWebsite(), "www.google.com");
    }

    @Test
    public void changeLocationWithoutAllowance() {
        User user1 = database.getUser(0);
        User user2 = database.getUser(1);
        ProfilePage profilePage = new ProfilePage(user1, user2);
        profilePage.ChangeLocation("www.google.com");
        assertNotEquals(user1.getLocation(), "www.google.com");
    }

    @Test
    public void getAlltweetsFromUser(){
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        List<Post> posts = profilePage.GetAllPostsFromUser();
        assertEquals(posts.size(), 3);
    }

    @Test
    public void getLastTweetsFromUser() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        int amount = 3;
        List<Post> posts = profilePage.GetLastTweetsFromUser(amount);
        assertEquals(posts.size(), amount);
    }

    @Test
    public void getLastTweetsFromUserAmountHigherThanListSize(){
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        int amount = 10;
        List<Post> posts = profilePage.GetLastTweetsFromUser(amount);
        assertNotEquals(amount, posts.size());
    }

    @Test
    public void getAllFollowers() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        List<User> followers = profilePage.GetAllFollowers();
        assertEquals(followers.size(), 3);
    }

    @Test
    public void getAllFollowing() {
        User user1 = database.getUser(0);
        ProfilePage profilePage = new ProfilePage(user1, user1);
        List<User> following = profilePage.GetAllFollowing();
        assertEquals(following.size(), 2);
    }
}