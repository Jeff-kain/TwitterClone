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
@Stateless
public class KwetterDaoJPA extends DaoFacade<User> implements KwetterDAO {

    @PersistenceContext(unitName = "KwetterProject_KwetterProject_1-web_war_1.0-SNAPSHOTPU")
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
    public boolean addFollowing(User invokingUser, User targetedUser) {
        targetedUser.getFollowers().add(invokingUser);
        invokingUser.getFollowing().add(targetedUser);

        em.merge(invokingUser);
        em.merge(targetedUser);
        return true;
    }

    @Override
    public boolean removeFollowing(User invokingUser, User targetUser) {
        targetUser.getFollowers().remove(invokingUser);
        invokingUser.getFollowing().remove(targetUser);

        em.merge(invokingUser);
        em.merge(targetUser);

        return true;
    }

    @Override
    public List<Kweet> getKweetsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User find(String username) {
        User u = em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username).getSingleResult();
        return u;
    }

    @Override
    public List<User> getFollowing(User user) {
        List<User> followers;
        followers = em.createNamedQuery("User.getFollowing").setParameter("Id", user.getId()).getResultList();
        return followers;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users;
        users = em.createNamedQuery("User.findAll").getResultList();
        return users;
    }

    @Override
    public List<Kweet> findAllKweets() {
        List<Kweet> kweets;
        kweets = em.createNamedQuery("Kweet.findAll").getResultList();
        return kweets;
    }

}
