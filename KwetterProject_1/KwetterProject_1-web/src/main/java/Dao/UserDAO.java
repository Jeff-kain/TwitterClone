/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import Exceptions.KwetterException;
import java.util.List;

/**
 *
 * @author jeffrey
 */
public interface UserDAO {

    User findUser(String username);

    void createUser(User user) throws KwetterException;

    void updateUser(User user) throws KwetterException;

    void removeUser(User user) throws KwetterException;

    List findAllUsers();

    User find(int id);
    
    List<User> getFollowing(String userName);

    List getFollowers(String userName);
}
