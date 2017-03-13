/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author jeffrey
 */
@Path("/kwetter-api")
public class KwetterRestApi {

    @Inject
    private KwetterService kwetterService;

    @GET
    @Path("/test")
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!!</body></h1></html>";
    }

    @GET
    @Path("/users")
    @Produces({APPLICATION_JSON})
    public Response getAllUsers() {
        List users = kwetterService.findAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/kweets")
    @Produces({APPLICATION_JSON})
    public Response getAllKweets() {
        List Kweets = kwetterService.findAllKweets();
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/kweets/{userName}")
    @Produces({APPLICATION_JSON})
    public Response getRecentKweets(@PathParam("userName") String userName) {
        List Kweets = kwetterService.findRecentKweets(userName);
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/followers/{userName}")
    @Produces({APPLICATION_JSON})
    public Response getFollowers(@PathParam("userName") String userName) {
        List followers = kwetterService.getFollowers(userName);
        return Response.ok(followers).build();
    }

    @GET
    @Path("/getList")
    @Produces({APPLICATION_JSON})
    public Response getList() {
        Collection<String> strings;
        strings = new ArrayList<String>();
        String t = "test1";
        String a = "test2";
        strings.add(a);
        strings.add(t);
        return Response.ok(strings).build();
    }

    @GET
    @Path("/getKweetsByUser/{userName}")
    @Produces({APPLICATION_JSON})
    public Response getKweetsByUser(@PathParam("userName") String userName) {
        final List KweetsByUser = kwetterService.findKweetsByUser(userName);
        return Response.ok(KweetsByUser).build();
    }

//    @GET
//    @Path("/{username}")
//    @Produces({APPLICATION_JSON, APPLICATION_XML})
//    public User getUser(@PathParam("username")String username) {
//        return kwetterService.find(username);
//    }
//
//    @PUT
//    @Path("/{username}")
//    @Produces({APPLICATION_JSON, APPLICATION_XML})
//    public User updateUser(@PathParam("username") String username, User user) {
//        if (user.getUserName().equalsIgnoreCase(username)) {
//            kwetterService.updateUser(user);
//            return user;
//        } else {
//            throw new RuntimeException("Failure");
//        }
//    }
//    @GET
//    @Path("/{username}/tweets")
//    @Produces({APPLICATION_JSON, APPLICATION_XML})
//    public List<Kweet> getKweets(@PathParam("username") String username) {
//        return kwetterService.fin(username);
//    }
}
