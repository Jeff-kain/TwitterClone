/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Domain.User;
import Service.KwetterService;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author jeffrey
 */
@ManagedBean
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private KwetterService kwetterService;

    private String userName;
    private User user;
    private List<User> followers;
    private String filter;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        if (user == null) {
            user = kwetterService.findUser(userName);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFollowers() {
        if (followers == null) {
            followers = kwetterService.getFollowers(userName);
        }
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

}
