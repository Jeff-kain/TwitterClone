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

    public void registerUser(User user) throws UserException {
        try {
            userDAO.createUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't register user", e);
        }
    }

    public void updateUser(User user) throws UserException {
        try {
            userDAO.updateUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't update user", e);
        }
    }

    public void removeUser(User user) throws UserException {
        try {
            userDAO.updateUser(user);
        } catch (KwetterException e) {
            throw new UserException("Can't remove user", e);
        }
    }

    public void followUser(User follower, User followee) throws UserException {
        follower.addFollow(followee);
        try {
            userDAO.updateUser(follower);
        } catch (KwetterException e) {
            throw new UserException("Can't update user", e);
        }

    }

    public User find(String username) {
        return userDAO.find(username);
    }

    public List getFollowers(String userName) {
        return userDAO.getFollowers(userName);
    }

    public List getFollowing(String userName) {
        return userDAO.getFollowing(userName);
    }

    public List findAll() {
        List allUsers = userDAO.findAllUsers();
        return allUsers;
    }

    public List findAllKweets() {
        return kwetterDAO.findAllKweets();
    }

    public List findRecentKweets(String userName) {
        return kwetterDAO.findRecentKweets(userName);
    }

    public List findKweetsByUser(String userName) {
        return kwetterDAO.getKweetsByUser(userName);
    }

    public void unfollowUser(User follower, User followee) throws UserException {
        follower.removeFollow(followee);
        try {
            userDAO.updateUser(follower);
        } catch (KwetterException e) {
            throw new UserException("Can't update user", e);
        }
    }

    public void createKweet(Kweet kweet) {
        kwetterDAO.createKweet(kweet);
    }
}
