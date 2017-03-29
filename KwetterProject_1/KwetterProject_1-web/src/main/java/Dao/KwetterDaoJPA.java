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

    /**
     * Create a kweet and add it to the database
     *
     * @param kweet
     */
    @Override
    public void createKweet(Kweet kweet) {
        em.persist(kweet);
    }

    /**
     * remove a kweet from the database
     *
     * @param userName
     * @param kweetid
     */
    @Override
    public void removeKweet(String userName, int kweetid) {
        Kweet k = find(kweetid);
        em.remove(k);
    }

    /**
     * find a kweet by id in the databases
     *
     * @param kweetid
     * @return kweet
     */
    @Override
    public Kweet find(int kweetid) {
        return em.find(Kweet.class, kweetid);
    }

    /**
     * add a follower to a user and add it to the database
     *
     * @param invokingUser
     * @param targetedUser
     * @return boolean
     */
//    @Override
//    public boolean addFollowing(User invokingUser, User targetedUser) {
//        targetedUser.getFollowers().add(invokingUser);
//        invokingUser.getFollowing().add(targetedUser);
//
//        em.merge(invokingUser);
//        em.merge(targetedUser);
//        return true;
//    }
//
//    /**
//     * remove a follower of a user and change it in the database
//     * @param invokingUser
//     * @param targetUser
//     * @return boolean
//     */
//    @Override
//    public boolean removeFollowing(User invokingUser, User targetUser) {
//        targetUser.getFollowers().remove(invokingUser);
//        invokingUser.getFollowing().remove(targetUser);
//
//        em.merge(invokingUser);
//        em.merge(targetUser);
//
//        return true;
//    }
    /**
     * Get a list of kweets by user
     *
     * @param userName
     * @return kweets
     */
    @Override
    public List getKweetsByUser(String userName) {
        return em.createNamedQuery("Kweet.findKweetsByUser").setParameter("userName", userName)
                .getResultList();
    }

    /**
     * Get a list of all kweets in the database
     *
     * @return kweets
     */
    @Override
    public List<Kweet> findAllKweets() {
        List<Kweet> kweets;
        kweets = em.createNamedQuery("Kweet.findAll").getResultList();
        return kweets;
    }

    @Override
    public List<Kweet> findMentions(String userName) {
        List<Kweet> kweets;
        kweets = em.createNamedQuery("Kweet.findMentions").setParameter("userName", userName).getResultList();
        return kweets;
    }

    /**
     * Get a list of all recent kweets by a user
     *
     * @param username
     * @return kweets
     */
    @Override
    public List findRecentKweets(String username) {
        List kweets;
        kweets = em.createNamedQuery("Kweet.findRecentKweets").setParameter("userName", username).setMaxResults(10).getResultList();
        return kweets;
    }

    @Override
    public void updateKweet(Kweet kweet) {
        edit(kweet);
    }

    @Override
    public List<Kweet> findTrendyKweets(String trend) {
        List<Kweet> kweets;
        kweets = em.createNamedQuery("Kweet.findByTrend").setParameter("trend", trend).getResultList();
        return kweets;
    }

}
