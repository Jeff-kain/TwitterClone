/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.UserDAO;
import Domain.Token;
import Domain.User;
import Exceptions.AuthenticationException;
import Utils.AuthState;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author jeffrey
 */
@Stateless
public class AuthenticationServiceImpl {

    @Inject
    UserDAO userDAO;

    @Inject
    AuthState authState;

    /**
     * Tries to see if the user already has a token, if not it tries to authenticate the user
     * (This method doesn't create a token, look at issueToken().
     * @param username
     * @param password
     * @throws AuthenticationException
     */
    public void authenticateUser(String username, String password) throws AuthenticationException {
        //Check if the authenticating user already has a valid token
        //if it doesn't create a new token for this user
        User user = userDAO.findUser(username);

        //Check if password is invalid
//        if (!user.getPassword().equals(password)) {
//            throw new AuthenticationException("Incorrect Credentials");
//        }
    }

    /**
     * Creates a token and adds it to the requesting user
     * @param username
     * @param token
     */
    public void issueToken(String username, String token) {
        int tokenDuration = 10;
        Date expiryDate = DateUtils.addMinutes(new Date(), tokenDuration);

        authState.addToken(new Token(username, token, expiryDate));
    }
}
