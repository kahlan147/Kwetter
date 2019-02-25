package DAO;

import Backend.Classes.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Niels Verheijen on 22/02/2019.
 */
@Stateless
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        em.merge(user);
        return true;
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUserFollowers(Long id) {
        User user = getUser(id);
        return user.getAllFollowers();
    }

    @Override
    public List<User> getUserFollowing(Long id) {
        User user = getUser(id);
        return user.getAllFollowing();
    }

    @Override
    public boolean follow(User follower, User followed) {
        follower.addToFollowing(followed);
        updateUser(follower);
        return true;
    }

    @Override
    public boolean unfollow(User follower, User followed) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
