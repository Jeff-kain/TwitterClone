/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author jeffrey
 */
@RequestScoped
public class LoggedUserBean {

    @Inject
    private KwetterService service;

    @ManagedProperty(value = "#{param.user}")
    private String username;
    private User user;

    private String kweetContent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        if (user == null) {
            service.findUser(username);
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

    public List<Kweet> getTimelineKweets() {
        List<Kweet> kweets = new ArrayList<>();
        for (User user : getUser().getFollowing()) {
            kweets.addAll(service.findKweetsByUser(user.getUserName()));
        }
        kweets.addAll(service.findKweetsByUser(user.getUserName()));
        return kweets;
    }

}
