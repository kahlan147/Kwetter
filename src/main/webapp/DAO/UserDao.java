package main.webapp.DAO;

import main.webapp.Backend.Classes.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class UserDao implements Dao<User> {

    private List<User> users = new ArrayList<>();

    public UserDao(){

    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int)id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, Object[] params) {
        user.setName(Objects.requireNonNull((String)params[0], "Name cannot be null"));
        user.setPassword(Objects.requireNonNull((String)params[1], "Email cannot be null"));
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
