package main.webapp.Backend.Classes;

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
    private long id;
    private String name;
    private String password;
    private String bio;
    private String location;
    private String website;
    private boolean isMod;
    private List<User> followers;
    private List<User> following;
    private List<Post> posts;

    // <editor-fold defaultstate="collapsed" desc="Gets Sets">

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

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

    public void removeFromFollowers(User user){
        followers.remove(user);
    }

    public List<User> getAllFollowers(){
        return followers;
    }

    public void addToFollowing(User user){
        following.add(user);
        user.addToFollowers(this);
    }

    public void removeFromFollowing(User user){
        following.remove(user);
        user.removeFromFollowers(this);
    }

    public List<User> getAllFollowing(){
        return following;
    }

    public void addToPosts(Post post){
        posts.add(post);
    }

    public List<Post> getAllPosts(){
        return posts;
    }

    public List<Post> getLastPosts(int amount){
        List<Post> lastPosts = new ArrayList<>();
        if(amount > posts.size()){
            amount = posts.size();
        }
        for(int x = posts.size(); x < posts.size()-amount; x--){
            lastPosts.add(posts.get(x));
        }
        return lastPosts;
    }

    // </editor-fold>

    public User(String name, String password){
        setName(name);
        setPassword(password);
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
    }

}
