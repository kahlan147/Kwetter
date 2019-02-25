package Service;

import Classes.User;
import DAO.IUserDao;
import DAO.UserDao;

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

    public User getUser(long id){
        return userDao.getUser(id);
    }

    public boolean removeUser(Long id){
        return userDao.deleteUser(id);
    }

    public User saveUser(User user){
        return userDao.createUser(user);
    }

    public List<User> getAllUsers(){return userDao.getAllUsers();}

    public boolean updateUser(long id, User user){
        user.setId(id);
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
}
