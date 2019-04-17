package pages;

import Classes.Post;
import Classes.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class Homepage {

    private User currentUser;

    public Homepage(User currentUser){
        this.currentUser = currentUser;
    }

    public List<Post> GetTweetsBy(String search){
        throw(new NotImplementedException());
    }

    public void PostTweet(String message){
        Post post = new Post(message);
        post.setPoster(currentUser);
    }

    public void PostTweet(String message, Post reactionTo){
        Post post = new Post(message);
        post.setPoster(currentUser);
        post.setIsReaction(true);
        post.addToReactions(reactionTo);
    }

    public List<Post> GetTweetsOfSelfAndFollowing(){
        List<Post> allPosts = new ArrayList<>();
        allPosts.addAll(currentUser.getAllPosts());
        for(User user : currentUser.getAllFollowing()){
            allPosts.addAll(user.getAllPosts());
        }
        return allPosts;
    }
}
