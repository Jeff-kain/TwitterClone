/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author jeffrey
 */
@NamedQueries({
    @NamedQuery(name = "Kweet.findAll", query = "SELECT k FROM Kweet k"),
    @NamedQuery(name = "Kweet.findKweetsByUser", query = "SELECT k FROM Kweet k,"
            + " User u WHERE user_id IN (SELECT id FROM User u "
            + "WHERE userName = :userName)"),
})
@Entity
public class Kweet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Date postDate;
    
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    
    public Kweet () {
        
    }
  
    public Kweet (String content, User owner) {
        this.content = content;
        this.user = owner;
        this.postDate = new Date();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
        public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return postDate;
    }

    public User getOwner() {
        return user;
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
        if (!(object instanceof Kweet)) {
            return false;
        }
        Kweet other = (Kweet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.Tweet[ id=" + id + " ]";
    }
    
}
