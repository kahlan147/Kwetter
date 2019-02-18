package main.webapp.DAO;

import main.webapp.Backend.Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 15/02/2019.
 */
public interface UserDao {
    boolean createUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
    User getUser(Long id);
    List<User> getUserFollowers(Long id);
    List<User> getUserFollowing(Long id);
    boolean follow(User follower, User followed);
    boolean unfollow(User follower, User followed);
    List<User> getAllUsers();
}
