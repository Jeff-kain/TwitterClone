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

    @Override
    protected EntityManager getEntityManager() {
        return em;
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
    public List<Kweet> getKweetsByUser(String userName) {
        return em.createNamedQuery("Kweet.findKweetsByUser", Kweet.class)
                .getResultList();
    }

    @Override
    public List<Kweet> findAllKweets() {
        List<Kweet> kweets;
        kweets = em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();
        return kweets;
    }

}
