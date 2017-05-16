/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Authorization.Secured;
import Domain.Kweet;
import Domain.Kweetcontent;
import Domain.User;
import Exceptions.UserException;
import Service.KwetterService;
import Utils.PermissionsEnum;
import io.swagger.annotations.Api;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author jeffrey
 */
@Api(value = "Kwetter")
@Path("/kwetter-api")
@Produces({APPLICATION_JSON})
@Secured(PermissionsEnum.USER)
public class KwetterRestApi {

    @Inject
    private KwetterService kwetterService;
    private User user;
    private User visit;

    @Context
    UriInfo uriInfo;

    List<Kweet> timeline;
    @Context
    SecurityContext securityContext;

    @PostConstruct
    public void postcontruct() {
        try {
            Principal principal = securityContext.getUserPrincipal();
            String username = principal.getName();
            user = kwetterService.findUser(username);
        } catch (Exception e) {

        }
    }

    @GET
    @Path("/users")
    //@Secured(PermissionsEnum.ADMIN)
    public Response getAllUsers() {
        final List<User> users = kwetterService.findAll();
        for(User u : users) {
            u.setSelf(Link.fromUri(uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(),"getVisitUser").build(u.getUserName())).rel("self").type("GET").build());
        }
        return Response.ok(users).build();
    }

    @GET
    @Path("/kweets")
    public Response getAllKweets() {
        final List<Kweet> Kweets = kwetterService.findAllKweets();
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/kweets/timeline")
    public List<Kweet> getTimeLineKweets() {
        timeline = new ArrayList<>();
        for (User user : kwetterService.getFollowing(user.getUserName())) {
            timeline.addAll(kwetterService.findKweetsByUser(user.getUserName()));
        }
        List<Kweet> kweetsbyuser = kwetterService.findKweetsByUser(user.getUserName());
        timeline.addAll(kweetsbyuser);
        return timeline;
    }

    @GET
    @Path("/trends")
    public Response getTrends() {
        List<String> trends = new ArrayList<>();
        timeline = getTimeLineKweets();
        for (Kweet k : timeline) {
            if (k.getTrends() != null) {
                for (String s : k.getTrends()) {
                    trends.add("#" + s);
                }
            }
        }
        return Response.ok(trends).build();
    }

    @GET
    @Path("/kweets/recent")
    public Response getRecentKweets() {
        final List<Kweet> Kweets = kwetterService.findRecentKweets(user.getUserName());
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/kweets/recent/{username}")
    public Response getRecentKweetsByUser(@PathParam("username") String userName) {
        final List<Kweet> Kweets = kwetterService.findRecentKweets(userName);
        return Response.ok(Kweets).build();
    }

    @GET
    @Path("/followers")
    public Response getFollowers() {
        final List<User> followers = kwetterService.getFollowers(user.getUserName());
        return Response.ok(followers).build();
    }

    @GET
    @Path("/following")
    public Response getFollowing() {
        final List<User> followers = kwetterService.getFollowing(user.getUserName());
        return Response.ok(followers).build();
    }

    @GET
    @Path("/following/{username}")
    public Response getFollowingvisit(@PathParam("username") String userName) {
        final List<User> followers = kwetterService.getFollowing(userName);
        return Response.ok(followers).build();
    }

    @GET
    @Path("/kweets")
    public Response getKweetsByUser() {
        final List<Kweet> KweetsByUser = kwetterService.findKweetsByUser(user.getUserName());
        return Response.ok(KweetsByUser).build();
    }

    @GET
    @Path("/mentions")
    public Response getMentionsByUser() {
        //User user = kwetterService.findUser(userName);
        List<Kweet> mentions = user.getMentions();
        return Response.ok(mentions).build();
    }

    @GET
    @Path("/trends/{trend}")
    public Response getKweetsByTrend(@PathParam("trend") String trend) {
        List<Kweet> trends = kwetterService.findTrendyKweets(trend);
        return Response.ok(trends).build();
    }

    @POST
    @Path("kweets")
    @Consumes({APPLICATION_JSON})
    public Response postKweet(Kweetcontent content) {
        //User user = kwetterService.findUser(userName);
        Kweet kweet = new Kweet(content.getContent(), user);
        kwetterService.createKweet(kweet);
        return Response.ok().build();
    }

    @POST
    @Path("addfollowing/{followee}")
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
    @Path("/{kweet}")
    public Response deleteKweet(@PathParam("userName") String userName, @PathParam("kweet") int kweetId) {
        kwetterService.removeKweet(userName, kweetId);
        return Response.ok().build();
    }

    @GET
    @Path("currentuser")
    public User getCurrentUser() {
        return user;
    }

    @GET
    @Path("visit/{username}")
    public User getVisitUser(@PathParam("username") String userName) {
        visit = kwetterService.findUser(userName);
        return visit;
    }

//       @Inject
//    private JMSContext context;
// 
//    @Resource(lookup = "java:jboss/exported/jms/queue/kwettergo")
//    Queue queue;
// 
// 
////Code:
// 
//            TextMessage message = context.createTextMessage();
//            message.setText("KWETTER_API");
//            message.setIntProperty("id", id);
//            message.setStringProperty("text", text);
//            context.createProducer().send(queue, message);
}
