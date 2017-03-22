/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author jeffrey
 */
import Exception.AuthenticationException;
import Domain.Token;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class AuthState {

    List<Token> tokenList;

    public AuthState() {
        this.tokenList = new ArrayList<Token>();
    }

    /**
     * Check if there exists a token for the given user
     *
     * @param username
     * @return boolean
     */
    public boolean isTokenValidForUsername(String username) {
        for (Token t : tokenList) {
            if (t.getUsername().equals(username) && checkTokenIsValid(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given token exists
     *
     * @param token
     * @return boolean
     */
    public boolean isTokenValid(String token) {
        for (Token t : tokenList) {
            if (t.getToken().equals(token)) {
                return checkTokenIsValid(t);
            }
        }
        return false;
    }

    /**
     * Adds token with accompanying user
     *
     * @param token
     */
    public void addToken(Token token) {
        tokenList.add(token);
    }

    public String getUsernameByToken(String token) throws AuthenticationException {

        for (Token t : tokenList) {
            if (t.getToken().equals(token)) {
                return t.getUsername();
            }
        }

        throw new AuthenticationException("User not authenticated");
    }

    private boolean checkTokenIsValid(Token token) {
        boolean tokenIsValid = token.getExpirationDate().after(new Date());

        if (!tokenIsValid) {
            tokenList.remove(token);
        }

        return tokenIsValid;
    }
}
