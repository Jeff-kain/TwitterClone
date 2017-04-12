/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author jeffrey
 */
public class Accesstoken {

    /**
     * Class that creates a response for tokens
     * Example:
     * "access_token":"token_some_token",
     * "token_type":"bearer",
     * "expires_in":3600
     */


    private String access_token;
    private String token_type;
    private int  expires_in; //seconds

    public Accesstoken(String access_token, String token_type, int expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
