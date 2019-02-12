package main.webapp.Backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Niels Verheijen on 11/02/2019.
 */
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String password;
    private String bio;
    private String location;
    private String website;
    private boolean isMod;
    private List<User> followers;
    private List<User> following;

    // <editor-fold defaultstate="collapsed" desc="Gets Sets">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isMod() {
        return isMod;
    }

    public void setMod(boolean mod) {
        isMod = mod;
    }

    public void addToFollowers(User user){
        followers.add(user);
    }

    public void addToFollowing(User user){
        following.add(user);
    }

    // </editor-fold>


    public User(String name, String password){
        this.name = name;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

}
