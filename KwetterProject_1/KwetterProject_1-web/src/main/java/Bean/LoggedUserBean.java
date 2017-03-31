/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private String role;
    private User user;

    private String kweetContent;

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
//            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//            String userName = context.getUserPrincipal().getName();
            String userName = "Jeff";
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
        getRequest().getSession().invalidate();
        return "logout";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public List<Kweet> getTimelineKweets() {
        user = service.findUser("Jeff");
        List<Kweet> kweets = new ArrayList<>();
//        for (User user : getUser().getFollowing()) {
//            kweets.addAll(service.findRecentKweets(user.getUserName()));
//        }
        kweets.addAll(service.findRecentKweets(user.getUserName()));
        return kweets;
    }
    
    public List<User> getFollowing() {
        List<User> users = new ArrayList<>();
        users= service.getFollowing("Jeff");
        return users;
    }
}
