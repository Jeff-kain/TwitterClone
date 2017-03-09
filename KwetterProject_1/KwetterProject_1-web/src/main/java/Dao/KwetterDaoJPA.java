/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author jeffrey
 */
@Stateless @JPA
public class KwetterDaoJPA extends DaoFacade<User> implements KwetterDAO {
    
    @PersistenceContext(unitName = "KwetterProject_Kwetter-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public KwetterDaoJPA() {
        super(User.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void createUser(User user) {
        em.persist(user);
    }
    
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }
    
    @Override
    public List<User> getFollowers(User user) {
        List<User> followers;
        followers = em.createNamedQuery("User.getFollowers").setParameter("Id", user.getId()).getResultList();
        return followers;
    }
    
    @Override
    public User findUserById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User findUserByName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void createKweet(Kweet kweet) {
        em.persist(kweet);
    }
    
    @Override
    public List<Kweet> getKweetsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
