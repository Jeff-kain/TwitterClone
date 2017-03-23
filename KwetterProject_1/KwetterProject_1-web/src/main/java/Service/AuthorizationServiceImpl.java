/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.UserDAO;
import Utils.PermissionsEnum;
import javax.inject.Inject;

/**
 *
 * @author jeffrey
 */
public class AuthorizationServiceImpl {

    @Inject
    UserDAO userDAO;

    public PermissionsEnum getUserPermission(String username) {
        return userDAO.getUserPermission(username);
    }
}
