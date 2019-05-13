package Classes;


import REST.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Niels Verheijen on 11/02/2019.
 */
@NamedQueries({
        @NamedQuery(name = "user.all", query = "SELECT u from User as u"),
        @NamedQuery(name = "user.some", query = "SELECT u from User as u WHERE u.name like :uName"),
        @NamedQuery(name="user.login", query = "SELECT u from User as u WHERE u.name like :uName AND u.password like :uPassword"),
})

@Entity
public class User extends ResourceSupport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;
    private String password;
    private String bio;
    private String location;
    private String website;
    private boolean isMod;

    @ManyToMany(mappedBy = "following")
    @JsonbTransient
    private List<User> followers;

    @ManyToMany
    private List<User> following;

    @OneToMany
    private List<Post> posts;

    // <editor-fold defaultstate="collapsed" desc="Gets Sets">

    public void setUserId(long id){
        this.userId =id;
    }

    public long getUserId(){
        return userId;
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
        //super.add(linkTo(UserResource.class).slash(user.getUserId()).withRel("Follower"));
        super.add(new Link("http://localhost:8080/Kwetter/api/users/" + user.getUserId(), "Follower"));
    }

    public void removeFromFollowers(User user){
        followers.removeIf(U -> (U.getUserId() == user.getUserId()));
    }

    public List<User> getAllFollowers(){
        return followers;
    }

    public void addToFollowing(User user){
        following.add(user);
        //super.add(linkTo(UserResource.class).slash(user.getUserId()).withRel("Following"));
        super.add(new Link("http://localhost:8080/Kwetter/api/users/" + user.getUserId(), "Following"));
        user.addToFollowers(this);
    }

    public void removeFromFollowing(User user){
        following.removeIf(U -> (U.getUserId() == user.getUserId()));
        user.removeFromFollowers(this);
    }

    @JsonbTransient
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
        for(int x = posts.size(); x > posts.size()-amount; x--){
            lastPosts.add(posts.get(x-1));
        }
        return lastPosts;
    }

    // </editor-fold>

    public User(){
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public User(String name, String password){
        setName(name);
        setPassword(password);
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
        setMod(true);
    }

    public User(long userId, String name, String password, String bio, String location, String website, boolean isMod){
        setUserId(userId);
        setName(name);
        setPassword(password);
        setBio(bio);
        setLocation(location);
        setWebsite(website);
        setMod(isMod);
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
    }

}
