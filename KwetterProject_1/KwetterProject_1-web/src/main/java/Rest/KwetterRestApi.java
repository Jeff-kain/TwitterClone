/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Authorization.Secured;
import Domain.Kweet;
import Domain.User;
import Exceptions.UserException;
import Service.KwetterService;
import Utils.PermissionsEnum;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author jeffrey
 */
@Api(value = "Kwetter")
@Path("/kwetter-api")
@Produces({APPLICATION_JSON})
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
    @Secured(PermissionsEnum.ADMIN)
    public Response getAllUsers() {
        final List<User> users = kwetterService.findAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/kweets")
    public Response getAllKweets() {
        final List<Kweet> Kweets = kwetterService.findAllKweets();
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/{userName}/kweets/recent")
    public Response getRecentKweets(@PathParam("userName") String userName) {
        final List<Kweet> Kweets = kwetterService.findRecentKweets(userName);
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/{userName}/followers")
    public Response getFollowers(@PathParam("userName") String userName) {
        final List<User> followers = kwetterService.getFollowers(userName);
        return Response.ok(followers).build();
    }

    @GET
    @Path("/{userName}/following")
    public Response getFollowing(@PathParam("userName") String userName) {
        final List<User> followers = kwetterService.getFollowing(userName);
        return Response.ok(followers).build();
    }

    @GET
    @Path("/{userName}/kweets")
    public Response getKweetsByUser(@PathParam("userName") String userName) {
        final List<User> KweetsByUser = kwetterService.findKweetsByUser(userName);
        return Response.ok(KweetsByUser).build();
    }

    @POST
    @Path("{userName}/kweets")
    @Consumes(TEXT_PLAIN)
    public Response postKweet(@PathParam("userName") String userName, String content) {
        User user = kwetterService.findUser(userName);
        Kweet kweet = new Kweet(content, user);
        kwetterService.createKweet(kweet);
        return Response.ok().build();
    }

    @POST
    @Path("{userName}/addfollowing/{followee}")
    public Response postAddFollowing(@PathParam("userName") String userName, @PathParam("followee") String userNameFollowee) {
        User follower = kwetterService.findUser(userName);
        User followee = kwetterService.findUser(userNameFollowee);
        try {
            kwetterService.followUser(followee, follower);
            return Response.ok().build();
        } catch (UserException ex) {
            Logger.getLogger(KwetterRestApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.serverError().build();
    }

    @DELETE
    @Path("/{userName}/{kweet}")
    public Response deleteKweet(@PathParam("userName") String userName, @PathParam("kweet") int kweetId) {
        kwetterService.removeKweet(userName, kweetId);
        return Response.ok().build();
    }
}
