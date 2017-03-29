/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Utils.PermissionsEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author jeffrey
 */
@NamedQueries({
    @NamedQuery(name = "User.finduser", query = "SELECT u FROM User u WHERE userName =:userName"),
    @NamedQuery(name = "User.findAllUsers", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findFollowers", query = "SELECT u.id,u.userName FROM User u JOIN u.following f WHERE f.id = (SELECT u.id FROM User u WHERE userName=:userName)"),
    @NamedQuery(name = "User.findFollowing", query = "SELECT u.id,u.userName FROM User u JOIN u.followers f WHERE f.id = (SELECT u.id FROM User u WHERE userName=:userName)"),
})
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

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Kweet> kweets;

    @JoinTable(name = "follower", joinColumns = {
        @JoinColumn(name = "follower_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "following_id")
    })
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> following = new ArrayList();
    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> followers = new ArrayList();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Kweet> mentions = new ArrayList<Kweet>();

    private PermissionsEnum permission;

    @XmlTransient
    public List<Kweet> getMentions() {
        return mentions;
    }

    public void addMention(Kweet kweet) {
        this.mentions.add(kweet);
    }

    @XmlTransient
    public PermissionsEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionsEnum permission) {
        this.permission = permission;
    }

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

    @XmlTransient
    public List<User> getFollowing() {
        return following;
    }

    @XmlTransient
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

    @XmlTransient
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
