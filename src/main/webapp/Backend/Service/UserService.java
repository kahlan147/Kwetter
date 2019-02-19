package Backend.Service;

import Backend.Classes.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Niels Verheijen on 12/02/2019.
 */

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public User getUser(int id){
        return em.find(User.class, id);
    }

    public User saveUser(User user){
        em.persist(user);
        return user;
    }

    public List<User> getAlUser(){return em.createNamedQuery("user.all").getResultList();}
}
