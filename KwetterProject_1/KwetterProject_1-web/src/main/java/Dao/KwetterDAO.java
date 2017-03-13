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

    void createKweet(Kweet kweet);

    boolean removeFollowing(User invokingUser, User targetUser);

    boolean addFollowing(User invokingUser, User targetedUser);

    List getKweetsByUser(String userName);

    List findAllKweets();

    List findRecentKweets(String userName);
}
