/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Kweet;
import Domain.User;
import Exceptions.KwetterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

/**
 *
 * @author jeffrey
 */
@Stateless
public class KwetterDaoJPA extends DaoFacade<Kweet> implements KwetterDAO {

    @PersistenceContext(unitName = "KwetterProject_KwetterProject_1-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public KwetterDaoJPA() {
        super(Kweet.class);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createKweet(Kweet kweet) {
        em.persist(kweet);
    }

    @Override
    public void removeKweet(String userName, int kweetid) {
        Kweet k = find(kweetid);
        em.remove(k);
    }

    @Override
    public Kweet find(int kweetid) {
        return em.find(Kweet.class, kweetid);
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
    public List getKweetsByUser(String userName) {
        return em.createNamedQuery("Kweet.findKweetsByUser").setParameter("userName", userName)
                .getResultList();
    }

    @Override
    public List findAllKweets() {
        List kweets;
        kweets = em.createNamedQuery("Kweet.findAll").getResultList();
        return kweets;
    }

    @Override
    public List findRecentKweets(String username) {
        List kweets;
        kweets = em.createNamedQuery("Kweet.findRecentKweets").setParameter("userName", username).setMaxResults(10).getResultList();
        return kweets;
    }

}
