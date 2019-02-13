package main.webapp.DAO;

import java.util.List;
import java.util.Optional;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public interface Dao<T> {
    Optional<T> get(long id);
    List<T> getAll();
    void save(T t);
    void update(T t, Object[] params);
    void delete(T t);
}
