/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author jeffrey
 */
public class KwetterDaoCollImpl implements KwetterDAO {

    CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<>();

    @Override
    public void createKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    @Override
    public void removeKweet(String userName, int kweetid) {
        Kweet kweet = find(kweetid);
        if (kweet != null) {
            kweets.remove(kweet);
        }
    }

    @Override
    public List getKweetsByUser(String userName) {
        List<Kweet> userKweets = new ArrayList<>();

        for (Kweet kweet : kweets) {
            if (kweet.getOwner().getUserName() == userName) {
                userKweets.add(kweet);
            }
        }
        Collections.sort(userKweets, new Comparator<Kweet>() {
            @Override
            public int compare(Kweet kweet1, Kweet kweet) {
                return kweet.getDate().compareTo(kweet1.getDate());
            }
        });
        return userKweets;
    }

    @Override
    public List findAllKweets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Kweet find(int kweetid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findRecentKweets(String userName) {
        List<Kweet> userKweets = new ArrayList<>();

        for (Kweet kweet : kweets) {
            if (kweet.getOwner().getUserName()== userName) {
                if(userKweets.size() < 11) {
                    userKweets.add(kweet);
                }
            }
        }
        Collections.sort(userKweets, new Comparator<Kweet>() {
            @Override
            public int compare(Kweet kweet1, Kweet kweet) {
                return kweet.getDate().compareTo(kweet1.getDate());
            }
        });
        return userKweets;    }

    @Override
    public void updateKweet(Kweet kweet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
