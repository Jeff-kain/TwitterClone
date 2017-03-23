/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import Exceptions.KwetterException;
import Utils.PermissionsEnum;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author jeffrey
 */
public class UserDaoCollImpl implements UserDAO {

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public void removeUser(User user) throws KwetterException {
        User user1 = find(user.getId());
        if (user != null) {
            users.remove(user1);
        }
    }

    @Override
    public List findAllUsers() {
        List<User> users = new ArrayList<>();

        for (User user : users) {
            users.add(user);
        }
        return users;
    }

    @Override
    public User find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getFollowing(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getFollowers(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        @Override
    public User findUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser(User user) throws KwetterException {
        users.add(user);
    }

    @Override
    public void updateUser(User user) throws KwetterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermissionsEnum getUserPermission(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
