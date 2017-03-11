/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import java.util.List;

/**
 *
 * @author jeffrey
 */
public interface KwetterDAO {

    public void createUser(User user);

    public void updateUser(User user);

    public List<User> getFollowers(User user);

    public User findUserById(long id);

    public User findUserByName(String username);

    //maybe split here?
    public void createKweet(Kweet kweet);

    public boolean removeFollowing(User invokingUser, User targetUser);

    public boolean addFollowing(User invokingUser, User targetedUser);

    public List<Kweet> getKweetsByUser(User user);

    public User find(String username);


    public List<User> getFollowing(User user);

    public List<User> findAllUsers();

    public List<Kweet> findAllKweets();
}
