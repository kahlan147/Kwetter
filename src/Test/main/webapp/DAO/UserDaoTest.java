package main.webapp.DAO;

import Backend.Classes.User;
import DAO.UserDao;
import DAO.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Niels Verheijen on 18/02/2019.
 */
public class UserDaoTest {

    private UserDao userDao;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoImpl();
        user1 = new User("Testboy", "password");
        user2 = new User("Testgirl", "password");
    }

    @Test
    public void createUser(){
        userDao.createUser(user1);
        User returnedUser = userDao.getUser(user1.getId());
        assertEquals(user1, returnedUser);
    }

    @Test
    public void deleteUser(){
        userDao.createUser(user1);
        User returnedUser = userDao.getUser(user1.getId());
        assertEquals(user1, returnedUser);
        userDao.deleteUser(user1);
        returnedUser = userDao.getUser(user1.getId());
        assertNotEquals(returnedUser, user1);
    }

    @Test
    public void updateUser(){
        userDao.createUser(user1);
        user1.setName("Testboy2");
        userDao.updateUser(user1);
        User returnedUser = userDao.getUser(user1.getId());
        assertEquals(returnedUser.getName(), "Testboy2");

    }

    @Test
    public void getUser(){
        userDao.createUser(user1);
        User returnedUser = userDao.getUser(user1.getId());
        assertEquals(user1, returnedUser);
    }

    @Test
    public void getUserFollowers(){

    }

    @Test
    public void getUserFollowing(){

    }

    @Test
    public void follow(){

    }

    @Test
    public void unfullow(){

    }

    @Test
    public void getAllUsers(){

    }
}
