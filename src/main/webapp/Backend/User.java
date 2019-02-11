package main.webapp.Backend;

import java.util.List;
import java.util.UUID;

/**
 * Created by Niels Verheijen on 11/02/2019.
 */
public class User {

    public UUID Id;
    public String Name;
    public String Password;
    public String Bio;
    public String Location;
    public String Website;
    public boolean IsMod;
    public List<User> Followers;
    public List<User> Following;
}
