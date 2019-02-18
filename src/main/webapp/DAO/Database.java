package main.webapp.DAO;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public interface Database {

    User getUser(long index);
    Post getPost(long index);
    List<User> getAllUsers();
    List<Post> getAllPosts();
}
