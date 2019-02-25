package Backend.Service;

import Backend.Classes.User;
import DAO.IUserDao;
import DAO.UserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */

@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    public User getUser(long id){
        return userDao.getUser(id);
    }

    public User saveUser(User user){
        return userDao.createUser(user);
    }

    //public List<User> getAlUser(){return em.createNamedQuery("user.all",User.class).getResultList();}

    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }

    public boolean follow(User follower, User followed){
        return userDao.follow(follower, followed);
    }
}
