/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Domain.Accesstoken;
import Domain.UserCredentials;
import Service.AuthenticationServiceImpl;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author jeffrey
 */
@Path("/authentication")
public class AuthenticationController {

    @Inject
    AuthenticationServiceImpl authenticationService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response authenticateUser(UserCredentials userCredentials) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(userCredentials.getUsername(), userCredentials.getPassword());

            // Issue a token for the user
            String token = issueToken(userCredentials.getUsername());

             Accesstoken aToken = new Accesstoken(token, "bearer", 600);
            // Return the token on the response
            return Response.ok(aToken).build();

        } catch (Exception e) {
            return Response.ok(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        authenticationService.authenticateUser(username, password);
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        authenticationService.issueToken(username, token);
        return token;
    }
}
