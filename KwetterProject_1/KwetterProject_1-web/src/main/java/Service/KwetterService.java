/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test1
 */
package Service;

import Dao.JPA;
import Dao.KwetterDAO;
import Dao.UserDAO;
import Domain.Kweet;
import Domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author jeffrey
 */
@Stateless
public class KwetterService {

    @Inject
    private KwetterDAO kwetterDAO;

    @Inject
    private UserDAO userDAO;

    public void setKwetterDAO(KwetterDAO dao) {
        kwetterDAO = dao;
    }

    public void setuserDAO(UserDAO dao) {
        userDAO = dao;
    }

    public void registerUser(User user) {
        userDAO.createUser(user);

    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    public void followUser(User follower, User followee) {
        follower.addFollow(followee);
        userDAO.updateUser(follower);
    }

    public User find(String username) {
        return userDAO.find(username);
    }

    public List<User> getFollowers(User user) {
        return userDAO.getFollowers(user);
    }

    public List<User> getFollowing(User user) {
        return userDAO.getFollowing(user);
    }

    public List findAll() {
        List allUsers = userDAO.findAllUsers();
        return allUsers;
    }

    public List<Kweet> findAllKweets() {
        return kwetterDAO.findAllKweets();
    }
    
    public List findRecentKweets(String userName) {
        return kwetterDAO.findRecentKweets(userName);
    }

    public List<Kweet> findKweetsByUser(String userName) {
        return kwetterDAO.getKweetsByUser(userName);
    }

    public void unfollowUser(User follower, User followee) {
        follower.removeFollow(followee);
        userDAO.updateUser(follower);
    }

    public void createKweet(Kweet kweet) {
        kwetterDAO.createKweet(kweet);
    }
}
