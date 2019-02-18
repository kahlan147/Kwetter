package main.webapp.DAO.MockData;

import main.webapp.Backend.Classes.Post;
import main.webapp.Backend.Classes.User;
import main.webapp.DAO.Dao;
import main.webapp.DAO.Database;
import main.webapp.DAO.PostDaoImpl;
import main.webapp.DAO.UserDaoImpl;

import java.util.List;
import java.util.Random;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class MockDaoDatabase implements Database {

    private Dao userDao;
    private Dao postDao;

    public MockDaoDatabase(){
        userDao = new UserDaoImpl();
        postDao = new PostDaoImpl();
        generateUsers();
        generatePosts();
    }

    public User getUser(long index){
        return (User) userDao.get(index).get();
    }

    public Post getPost(long index){
        return (Post) postDao.get(index).get();}

    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    public List<Post> getAllPosts(){
        return postDao.getAll();
    }

    private void generateUsers(){
        userDao.save(new User("jeff", "klokje"));
        userDao.save(new User("mark", "polka"));
        userDao.save(new User("Tom", "98213"));
        userDao.save(new User("Tim", "1231"));
        userDao.save(new User("Ben", "1235647"));
        userDao.save(new User("Bob", "afag"));
        userDao.save(new User("Amanda", "gs3a4"));
        userDao.save(new User("Lisbeth", "segas2"));
        userDao.save(new User ("Niko", "Bellick"));
        userDao.save(new User("Zayne", "Bard125"));

        for(User user : (List<User>)userDao.getAll()){
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

    private void followUser(long user1, long user2){
        ((User)userDao.get(user1).get()).addToFollowing((User)userDao.get(user2).get());
    }

    private void createPost(long user, boolean isReaction, int reactionTo){
        Post post = new Post(generateRandomStringOfSize(20));
        post.setPoster((User)userDao.get(user).get());
        post.setIsReaction(isReaction);
        if(isReaction) {
            ((Post)postDao.get(reactionTo).get()).addToReactions(post);
        }
        postDao.save(post);
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
