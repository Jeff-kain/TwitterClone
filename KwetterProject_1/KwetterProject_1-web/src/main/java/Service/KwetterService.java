/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test1
 */
package Service;

import Dao.KwetterDAO;
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

    public void setKwetterDAO(KwetterDAO dao) {
        kwetterDAO = dao;
    }

    public void registerUser(User user) {
        kwetterDAO.createUser(user);

    }

    public void updateUser(User user) {
        kwetterDAO.updateUser(user);
    }

    public void followUser(User follower, User followee) {
        follower.addFollow(followee);
        kwetterDAO.updateUser(follower);
    }

    public List<User> getFollowers(User user) {
        return kwetterDAO.getFollowers(user);
    }

    public void unfollowUser(User follower, User followee) {
        follower.removeFollow(followee);
        kwetterDAO.updateUser(follower);
    }

    public void createKweet(Kweet kweet) {
        kwetterDAO.createKweet(kweet);
    }
}
