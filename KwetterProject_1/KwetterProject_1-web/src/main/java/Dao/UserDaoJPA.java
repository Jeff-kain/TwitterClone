/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import Exceptions.KwetterException;
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

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public UserDaoJPA() {
        super(User.class);
    }

    @Override
    public User findUser(String userName) {
        List<User> users = em.createNamedQuery("User.finduser").setParameter("userName", userName).getResultList();
        return users.get(0);
    }

    @Override
    public void createUser(User user) throws KwetterException{
        em.persist(user);
    }

    @Override
    public void updateUser(User user) throws KwetterException{
        em.merge(user);
    }

    @Override
    public void removeUser(User user) throws KwetterException{
        em.remove(user);
    }

    @Override
    public List getFollowers(String userName) {
        return em.createNamedQuery("User.findFollowers").setParameter("userName", userName)
                .getResultList();
    }

    @Override
    public User find(int id) {
        return em.find(User.class, id);

    }

    @Override
    public List getFollowing(String userName) {
        return em.createNamedQuery("User.findFollowing").setParameter("userName", userName).getResultList();
    }

    @Override
    public List findAllUsers() {
        List users = em.createNamedQuery("User.findAllUsers")
                .getResultList();
        return users;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
