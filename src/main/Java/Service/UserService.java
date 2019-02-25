package Service;

import Classes.User;
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

    //public List<User> getAlUser(){return em.createNamedQuery("user.all",User.class).getResultList();}

    public boolean updateUser(long id, User user){
        user.setId(id);
        return userDao.updateUser(user);
    }

    public boolean follow(User follower, User followed){
        return userDao.follow(follower, followed);
    }
}
