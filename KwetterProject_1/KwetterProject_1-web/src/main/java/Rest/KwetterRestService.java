/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.*;

/**
 *
 * @author jeffrey
 */
@Path("/kwetter-api")
public class KwetterRestService {

    @Inject
    private KwetterService kwetterService;

    @GET
    @Path("/test")
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!!</body></h1></html>";
    }

    @GET
    @Path("/getAllUsers")
    @Produces({APPLICATION_JSON, APPLICATION_XML})
    public List<User> getAllUsers() {
        return kwetterService.findAll();
    }

    @GET
    @Path("/{username}")
    @Produces({APPLICATION_JSON, APPLICATION_XML})
    public User getUser(@PathParam("username") String username) {
        return kwetterService.find(username);
    }

    @PUT
    @Path("/{username}")
    @Produces({APPLICATION_JSON, APPLICATION_XML})
    public User updateUser(@PathParam("username") String username, User user) {
        if (user.getUserName().equalsIgnoreCase(username)) {
            kwetterService.updateUser(user);
            return user;
        } else {
            throw new RuntimeException("Failure");
        }
    }

//    @GET
//    @Path("/{username}/tweets")
//    @Produces({APPLICATION_JSON, APPLICATION_XML})
//    public List<Kweet> getKweets(@PathParam("username") String username) {
//        return kwetterService.fin(username);
//    }
}
