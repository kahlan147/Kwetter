package DAO.MockData;

import Backend.Classes.Post;
import Backend.Classes.User;
import DAO.*;
import DAO.IUserDao;

import java.util.List;
import java.util.Random;

/**
 * Created by Niels Verheijen on 13/02/2019.
 */
public class MockDaoDatabase implements IDatabase {

    private IUserDao userDao;
    private IPostDao postDao;

    public MockDaoDatabase(){
        userDao = new MockUserDao();
        postDao = new PostDaoImpl();
        generateUsers();
        generatePosts();
    }

    public User getUser(long index){
        return userDao.getUser(index);
    }

    public Post getPost(long index){
        return postDao.getAllPosts().get(Math.toIntExact(index));}

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }

    private void generateUsers(){
        userDao.createUser(new User("jeff", "klokje"));
        userDao.createUser(new User("mark", "polka"));
        userDao.createUser(new User("Tom", "98213"));
        userDao.createUser(new User("Tim", "1231"));
        userDao.createUser(new User("Ben", "1235647"));
        userDao.createUser(new User("Bob", "afag"));
        userDao.createUser(new User("Amanda", "gs3a4"));
        userDao.createUser(new User("Lisbeth", "segas2"));
        userDao.createUser(new User ("Niko", "Bellick"));
        userDao.createUser(new User("Zayne", "Bard125"));

        for(User user : userDao.getAllUsers()){
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
        userDao.getUser(user1).addToFollowing(userDao.getUser(user2));
    }

    private void createPost(long user, boolean isReaction, int reactionTo){
        Post post = new Post(generateRandomStringOfSize(20));
        post.setPoster(userDao.getUser(user));
        post.setIsReaction(isReaction);
        if(isReaction) {
            postDao.getAllPosts().get(reactionTo).addToReactions(post);
        }
        postDao.createPost(post);
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
