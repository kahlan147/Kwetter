package main.webapp.Backend.pages;

import main.webapp.Backend.Classes.User;
import main.webapp.MockData.MockDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class ProfilePageTest {

    MockDatabase database;

    @Before
    public void setUp() throws Exception {
        database = new MockDatabase();
    }

    @Test
    public void changeName() {
        User user = new User("test","test");
        //test
    }

    @Test
    public void changeBio() {
    }

    @Test
    public void changeWebsite() {
    }

    @Test
    public void changeLocation() {
    }

    @Test
    public void getLastTenTweetsFromUser() {
    }

    @Test
    public void getAllFollowers() {
    }

    @Test
    public void getAllFollowing() {
    }
}