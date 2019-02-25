package Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Niels Verheijen on 11/02/2019.
 */
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private Date date;
    private boolean isReaction;
    @ManyToOne
    private User poster;
    @OneToMany
    private List<Post> reactions;

    //<editor-fold defaultstate="collapsed" desc="Gets Sets">

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIsReaction() {
        return isReaction;
    }

    public void setIsReaction(boolean reaction) {
        isReaction = reaction;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
        poster.addToPosts(this);
    }

    public void addToReactions(Post post){
        reactions.add(post);
    }

    public List<Post> getReactions(){
        return reactions;
    }

    //</editor-fold>

    public Post(){

    }

    public Post(String message){
        reactions = new ArrayList<>();
        setMessage(message);
        setDate(new Date());
    }

    public Post(String message, User user){
        reactions = new ArrayList<>();
        setMessage(message);
        setPoster(user);
    }

    public Post(String message, Date date){
        reactions = new ArrayList<>();
        setMessage(message);
        setDate(date);
    }

    public Post(String message, User user, Date date){
        reactions = new ArrayList<>();
        setMessage(message);
        setDate(date);
        setPoster(user);
    }

}
