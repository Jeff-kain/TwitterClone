/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeffrey
 */
@Stateless
public class UserDaoJPA extends DaoFacade<User> implements UserDAO {

    @PersistenceContext(unitName = "KwetterProject_KwetterProject_1-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UserDaoJPA() {
        super(User.class);
    }

    @Override
    public User findUser(String username) {
        return em.find(User.class, username);
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
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> getFollowers(User user) {
        List<User> followers;
        followers = em.createNamedQuery("User.getFollowers").setParameter("Id", user.getId()).getResultList();
        return followers;
    }

    @Override
    public User find(String username) {
//        User u = em.createNamedQuery("User.findByUsername", User.class)
//                .setParameter("username", username).getSingleResult();
//        return u;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<User> getFollowing(User user) {
        List<User> followers;
        followers = em.createNamedQuery("User.getFollowing").setParameter("Id", user.getId()).getResultList();
        return followers;
    }

    @Override
    public List<User> findAllUsers() {
        // @SuppressWarnings("unchecked")
        return em.createNamedQuery("User.findAllUsers", User.class)
                .getResultList();
//        return results;
    }

    @Override
    public List<String> findAllFollowing(String username) {
        @SuppressWarnings("unchecked")
        final List<String> results = em
                .createNativeQuery(
                        "SELECT f.follows FROM Following f where f.user = ?1")
                .setParameter(1, username).getResultList();
        return results;
    }

    @Override
    public int countFollowers(String username) {
        Number count = (Number) em
                .createNativeQuery(
                        "SELECT count(f.user) FROM Following f where f.follows = ?1")
                .setParameter(1, username).getSingleResult();
        return count.intValue();
    }

    @Override
    public int countFollowing(String username) {
        Number count = (Number) em
                .createNativeQuery(
                        "SELECT count(f.follows) FROM Following f where f.user = ?1")
                .setParameter(1, username).getSingleResult();
        return count.intValue();
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
