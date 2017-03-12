/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import java.util.List;

/**
 *
 * @author jeffrey
 */
public interface UserDAO {

    User findUser(String username);

    void createUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    List<User> findAllUsers();

    public User find(String username);

    public List<User> getFollowing(User user);

    public List<User> getFollowers(User user);

    List<String> findAllFollowing(String username);

    int countFollowers(String username);

    int countFollowing(String username);
}
