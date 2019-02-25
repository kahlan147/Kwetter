package DAO;

import Backend.Classes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class MockUserDao implements IUserDao {

    private List<User> users;

    public MockUserDao(){
        users = new ArrayList<>();
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        user.setId(users.size());
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        users.remove(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        users.set(Math.toIntExact(user.getId()), user);
        return true;
    }

    @Override
    public User getUser(Long id) {
        return users.get(Math.toIntExact(id));
    }

    @Override
    public List<User> getUserFollowers(Long id) {
        return users.get(Math.toIntExact(id)).getAllFollowers();
    }

    @Override
    public List<User> getUserFollowing(Long id) {

        return users.get(Math.toIntExact(id)).getAllFollowing();
    }

    @Override
    public boolean follow(User follower, User followed) {
        followed.addToFollowing(follower);
        return true;
    }

    @Override
    public boolean unfollow(User follower, User followed) {
        followed.removeFromFollowing(follower);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
