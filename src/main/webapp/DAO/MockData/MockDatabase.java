package main.webapp.DAO.MockData;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;
import main.webapp.DAO.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class MockDatabase implements Database {
    private List<User> users;
    private List<Post> posts;

    public MockDatabase(){
        users = new ArrayList<>();
        posts = new ArrayList<>();
        generateUsers();
        generatePosts();
    }

    public User getUser(long index){
        return users.get((int)index);
    }

    public Post getPost(long index){return posts.get((int)index);}

    public List<User> getAllUsers(){
        return users;
    }

    public List<Post> getAllPosts(){
        return posts;
    }

    private void generateUsers(){
        users.add(new User("jeff", "klokje"));
        users.add(new User("mark", "polka"));
        users.add(new User("Tom", "98213"));
        users.add(new User("Tim", "1231"));
        users.add(new User("Ben", "1235647"));
        users.add(new User("Bob", "afag"));
        users.add(new User("Amanda", "gs3a4"));
        users.add(new User("Lisbeth", "segas2"));
        users.add(new User ("Niko", "Bellick"));
        users.add(new User("Zayne", "Bard125"));

        for(User user : users){
            user.setBio(generateRandomStringOfSize(20));
            user.setLocation(generateRandomStringOfSize(4));
            user.setWebsite(generateRandomStringOfSize(6));
        }
        followUser(1,0);
        followUser(5,0);
        followUser(4,0);
        followUser(2,1);
        followUser(5,2);
        followUser(0,5);
        followUser(6,2);
        followUser(3,8);
        followUser(8,4);
        followUser(0,1);
        followUser(9,3);
    }

    private void followUser(int user1, int user2){
        users.get(user1).addToFollowing(users.get(user2));
    }

    private void createPost(int user, boolean isReaction, int reactionTo){
        Post post = new Post(generateRandomStringOfSize(20));
        post.setPoster(users.get(user));
        post.setIsReaction(isReaction);
        if(isReaction) {
            posts.get(reactionTo).addToReactions(post);
        }
        posts.add(post);
    }

    private void generatePosts(){
        createPost(0, false, 0);
        createPost(1,true,0);
        createPost(0, false, 0);
        createPost(2, true, 1);
        createPost(5, true, 0);
        createPost(4, false, 0);
        createPost(8, true, 3);
        createPost(4, true, 2);
        createPost(5, true, 3);
        createPost(0, true, 4);
    }

    private String generateRandomStringOfSize(int size){
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for(int x = 0; x<size; x++){
            char character = (char)(random.nextInt(26) + 97);
            text.append(character);
        }
        return text.toString();
    }
}
