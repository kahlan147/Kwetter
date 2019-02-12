package main.webapp.Backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

/**
 * Created by Niels Verheijen on 11/02/2019.
 */
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String text;
    private Date date;
    private Time time;
    private boolean isReaction;
    private User poster;
    private List<Post> reactions;

    //<editor-fold defaultstate="collapsed" desc="Gets Sets">
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isReaction() {
        return isReaction;
    }

    public void setReaction(boolean reaction) {
        isReaction = reaction;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public void addToReactions(Post post){
        reactions.add(post);
    }
    //</editor-fold>

    public Post(){

    }

}
