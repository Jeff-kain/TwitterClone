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
import Exceptions.KwetterException;
import Exceptions.UserException;
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

    /**
     * register a user
     *
     * @param user
     * @throws UserException
     */
    public void registerUser(User user) throws UserException {
        try {
            userDAO.createUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't register user", e);
        }
    }

    /**
     * Update user info
     *
     * @param user
     * @throws UserException
     */
    public void updateUser(User user) throws UserException {
        try {
            userDAO.updateUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't update user", e);
        }
    }

    /**
     * remove a user
     *
     * @param user
     * @throws UserException
     */
    public void removeUser(User user) throws UserException {
        try {
            userDAO.updateUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't remove user", e);
        }
    }

    /**
     * Follow a user
     *
     * @param follower
     * @param followee
     * @throws UserException
     */
    public void followUser(User follower, User followee) throws UserException {
        follower.addFollower(followee);
        try {
            userDAO.updateUser(follower);
        } catch (KwetterException e) {
            throw new UserException("Can't follow user", e);
        }
    }

    /**
     * Find a user by id
     *
     * @param id
     * @return user
     */
    public User find(int id) {
        return userDAO.find(id);
    }

    /**
     * Find a user by username
     *
     * @param username
     * @return user
     */
    public User findUser(String username) {
        return userDAO.findUser(username);
    }

    /**
     * Get a list of followers
     *
     * @param userName
     * @return followers
     */
    public List<User> getFollowers(String userName) {
        return userDAO.getFollowers(userName);
    }

    /**
     * Get a list of users u are following
     *
     * @param userName
     * @return following
     */
    public List<User> getFollowing(String userName) {
        return userDAO.getFollowing(userName);
    }

    /**
     * Get a list of all users
     * @return users
     */
    public List<User> findAll() {
        List allUsers = userDAO.findAllUsers();
        return allUsers;
    }
    
    /**
     * Get a list of all kweets
     * @return kweets
     */
    public List<Kweet> findAllKweets() {
        return kwetterDAO.findAllKweets();
    }

    /**
     * Get a list of all recent kweets
     * @param userName
     * @return 
     */
    public List<Kweet> findRecentKweets(String userName) {
        return kwetterDAO.findRecentKweets(userName);
    }

    /**
     * Get a list of all kweets posted by a user
     * @param userName
     * @return kweets
     */
    public List<User> findKweetsByUser(String userName) {
        return kwetterDAO.getKweetsByUser(userName);
    }

    /**
     * Unfollow a user
     * @param follower
     * @param followee
     * @throws UserException 
     */
    public void unfollowUser(User follower, User followee) throws UserException {
        follower.removeFollower(followee);
        try {
            userDAO.updateUser(follower);
        } catch (KwetterException e) {
            throw new UserException("Can't unfollow user", e);
        }
    }
    
    /**
     * Create a kweet
     * @param kweet 
     */
    public void createKweet(Kweet kweet) {
        kwetterDAO.createKweet(kweet);
    }

    /**
     * Remove a kweet
     * @param userName
     * @param kweetid 
     */
    public void removeKweet(String userName, int kweetid) {
        kwetterDAO.removeKweet(userName, kweetid);
    }
}
