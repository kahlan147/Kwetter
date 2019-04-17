package DAO;

import Classes.Post;
import Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public interface IDatabase {

    User getUser(long index);
    Post getPost(long index);
    List<User> getAllUsers();
    List<Post> getAllPosts();
}
