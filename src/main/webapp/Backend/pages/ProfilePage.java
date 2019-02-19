package Backend.pages;

import javafx.geometry.Pos;
import Backend.Classes.Post;
import Backend.Classes.User;

import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class ProfilePage {

    private User owner;
    private User visitingUser;

    public ProfilePage(User owner, User visitingUser){
        this.owner = owner;
        this.visitingUser = visitingUser;
    }

    private boolean allowedToChangeData(){
        return owner == visitingUser;
    }

    public void ChangeName(String name){
        if(allowedToChangeData()){
            owner.setName(name);
        }
    }

    public void ChangeBio(String bio){
        if(allowedToChangeData()){
            owner.setBio(bio);
        }
    }

    public void ChangeWebsite(String website){
        if(allowedToChangeData()){
            owner.setWebsite(website);
        }
    }

    public void ChangeLocation(String location){
        if(allowedToChangeData()){
            owner.setLocation(location);
        }
    }

    public List<Post> GetLastTweetsFromUser(int amount){
        return owner.getLastPosts(amount);
    }

    public List<Post> GetAllPostsFromUser(){
        return owner.getAllPosts();
    }

    public List<User> GetAllFollowers(){
        return owner.getAllFollowers();
    }

    public List<User> GetAllFollowing(){
        return owner.getAllFollowing();
    }
}
