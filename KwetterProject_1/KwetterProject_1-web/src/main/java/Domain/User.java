/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author jeffrey
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String url;
//    @OneToMany(fetch=FetchType.LAZY, mappedBy="id", cascade = {CascadeType.PERSIST})
//    private List<User> following;
    @OneToMany
    private List<Kweet> kweets;
    
    @ManyToOne 
    private User parent;
    @OneToMany(mappedBy="parent")
    private Collection<User> following;

    public User(String userName, String url) {
        this.userName = userName;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Collection<User> getFollowing() {
        return following;
    }

    public void addFollow(User follow) {
        this.following.add(follow);
    }

    public void removeFollow(User follow) {
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.User[ id=" + id + " ]";
    }

}
