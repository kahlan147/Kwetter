package main.webapp.DAO;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class PostDao implements Dao<Post> {

    private List<Post> posts = new ArrayList<>();

    public PostDao(){

    }

    @Override
    public Optional<Post> get(long id) {
        return Optional.ofNullable(posts.get((int)id));
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public void save(Post post) {
        posts.add(post);
    }

    @Override
    public void update(Post post, Object[] params) {
        post.setMessage(Objects.requireNonNull((String)params[0]));
        post.setPoster(Objects.requireNonNull((User)params[1], "Email cannot be null"));
        posts.add(post);
    }

    @Override
    public void delete(Post post) {
        posts.remove(post);
    }
}
