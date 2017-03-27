/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import Exceptions.KwetterException;
import java.util.List;

/**
 *
 * @author jeffrey
 */
public interface KwetterDAO {

    void createKweet(Kweet kweet);
    
    void updateKweet(Kweet kweet);

    void removeKweet(String userName, int kweetid) ;

    List getKweetsByUser(String userName);

    List findAllKweets();
    
    Kweet find(int kweetid);

    List findRecentKweets(String userName);
}
