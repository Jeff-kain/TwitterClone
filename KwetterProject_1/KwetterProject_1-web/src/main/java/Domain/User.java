/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author jeffrey
 */
@NamedQueries({
    @NamedQuery(name = "User.finduser", query = "SELECT u FROM User u WHERE userName =:userName")
    ,

    @NamedQuery(name = "User.findAllUsers", query = "SELECT u.id, u.url, u.userName FROM User u")
    ,
    @NamedQuery(name = "User.findFollowers", query = "SELECT u.id,u.userName FROM User u JOIN u.following f WHERE f.id = (SELECT u.id FROM User u WHERE userName=:userName)")
    ,
    @NamedQuery(name = "User.findFollowing", query = "SELECT u.id,u.userName FROM User u JOIN u.followers f WHERE f.id = (SELECT u.id FROM User u WHERE userName=:userName)"),})
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userName", unique = true)
    private String userName;
    @Column(name = "url")
    private String url;
//    @OneToMany(fetch=FetchType.LAZY, mappedBy="id", cascade = {CascadeType.PERSIST})
//    private List<User> following;

    @OneToMany(mappedBy = "user")
    private List<Kweet> kweets;

    @JoinTable(name = "follower", joinColumns = {
        @JoinColumn(name = "follower_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "following_id")
    })
    @ManyToMany
    private List<User> following = new ArrayList();
    @ManyToMany(mappedBy = "following")
    private List<User> followers;
    
//    @OneToMany(mappedBy = "user")
//    private List<Heart> hearts;

    public User(String userName, String url) {
        this.userName = userName;
        this.url = url;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void addFollower(User follow) {
        this.following.add(follow);
    }

    public void removeFollower(User follow) {
        this.following.remove(follow);
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.User[ id=" + id + " ]";
    }

}
