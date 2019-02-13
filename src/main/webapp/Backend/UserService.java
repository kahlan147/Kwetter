package main.webapp.Backend;

import main.webapp.Backend.Classes.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
