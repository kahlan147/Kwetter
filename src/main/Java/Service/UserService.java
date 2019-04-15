package Service;

import Classes.User;
import DAO.IUserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */

@Stateless
public class UserService {

    @EJB
    private IUserDao userDao;

    public User logIn(String name, String password){return userDao.logIn(name,password);}

    public User getUser(long id){
        return userDao.getUser(id);
    }

    public List<User> getUsersByName(String name){return userDao.getUsersByName(name);}

    public boolean removeUser(Long id){
        User user = getUser(id);
        return userDao.deleteUser(user);
    }

    public User saveUser(User user){
        return userDao.createUser(user);
    }

    public List<User> getAllUsers(){return userDao.getAllUsers();}

    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }

    public boolean follow(long idFollower, long idFollowed){
        User follower = getUser(idFollower);
        User followed = getUser(idFollowed);
        return userDao.follow(follower, followed);
    }

    public boolean unFollow(long idFollower, long idFollowed){
        User follower = getUser(idFollower);
        User followed = getUser(idFollowed);
        return userDao.unfollow(follower,followed);
    }

    public List<User> getAllFollowing(long id){
        return userDao.getUserFollowing(id);
    }

    public List<User> getAllFollowers(long id){
        return userDao.getUserFollowers(id);
    }
}
