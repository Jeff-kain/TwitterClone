/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jeffrey
 */
@ManagedBean(name = "LoggedUserBean")
@SessionScoped
public class LoggedUserBean implements Serializable {

    @Inject
    private KwetterService service;

    private String username;
    private String password;
    private String url;
    private String bio;
    private String role;
    private String message;
    private User user;

    private String kweetContent;
    private List<Kweet> timeline;

    private int followerscount;
    private int followingcount;
    private int kweetscount;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFollowerscount() {
        List<User> users = new ArrayList<>();
        users = service.getFollowers(user.getUserName());
        followerscount = users.size();
        return followerscount;
    }

    public int getFollowingcount() {
        List<User> users = new ArrayList<>();
        users = service.getFollowing(user.getUserName());
        followingcount = users.size();
        return followingcount;
    }

    public int getKweetscount() {
        List<Kweet> kweetsbyuser = service.findKweetsByUser(user.getUserName());
        kweetscount = kweetsbyuser.size();
        return kweetscount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getUserName() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public User getUser() {
        if (user == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userName = context.getUserPrincipal().getName();
            user = service.findUser(userName);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKweetContent() {
        return kweetContent;
    }

    public void setKweetContent(String kweetContent) {
        this.kweetContent = kweetContent;
    }

    public boolean isUserAdmin() {
        return getRequest().isUserInRole("ADMIN");
    }

    public String logOut() {
        getRequest().getSession(false).invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/KwetterProject_1-web-1.0-SNAPSHOT/public/start.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoggedUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/KwetterProject_1-web-1.0-SNAPSHOT/login.xhtml";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void search() {
        
    }
    public void createKweet() {
        service.createKweet(new Kweet(message, user));
        message = "";
    }
    public List<Kweet> getRecentKweets() {
        List<Kweet> kweets = new ArrayList<>();
//        for (User user : getUser().getFollowing()) {
//            kweets.addAll(service.findRecentKweets(user.getUserName()));
//        }
        kweets.addAll(service.findRecentKweets(user.getUserName()));
        return kweets;
    }

    public List<Kweet> getTimeLineKweets() {
        user = service.findUser(user.getUserName());
        timeline = new ArrayList<>();
        for (User user : service.getFollowing(user.getUserName())) {
            timeline.addAll(service.findKweetsByUser(user.getUserName()));
        }
        List<Kweet> kweetsbyuser = service.findKweetsByUser(user.getUserName());
        kweetscount = kweetsbyuser.size();
        timeline.addAll(kweetsbyuser);
        return timeline;
    }

    public List<String> getTrends() {
        List<String> trends = new ArrayList<>();
        for (Kweet k : timeline) {
            if (k.getTrends() != null) {
                for (String s : k.getTrends()) {
                    trends.add("#" + s);
                }
            }
        }
        return trends;
    }

    public List<Kweet> getMentions() {
        List<Kweet> kweets = new ArrayList<>();
        kweets.addAll(user.getMentions());
        return kweets;
    }

    public List<User> getFollowing() {
        List<User> users = new ArrayList<>();
        users = service.getFollowing(user.getUserName());
        followingcount = users.size();
        return users;
    }

    public List<User> getFollowers() {
        List<User> users = new ArrayList<>();
        users = service.getFollowers(user.getUserName());
        followerscount = users.size();
        return users;
    }
}
