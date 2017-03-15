/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeffrey
 */
public class UserDaoJPATest {

    public UserDaoJPATest() {
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterProject_KwetterProject_1-web_war_1.0-SNAPSHOTPU2");
    private EntityManager em;
    private UserDaoJPA userDao;
    private EntityTransaction tx;
    private DatabaseCleaner dbc;

    User user1;
    User user2;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJPA();
        userDao.setEm(em);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCreateUser() throws Exception {
        user1 = new User("User", "www.user1.org");
        tx.begin();
        userDao.create(user1);
        tx.commit();
        User u = userDao.find(1);
        assertEquals(user1, u);
    }

    @Test
    public void testUpdateUser() throws Exception {
        user1 = new User("User", "www.user1.org");
        tx.begin();
        userDao.create(user1);
        tx.commit();
        user1.setUrl("www.UpdateUser.nl");
        userDao.updateUser(user1);
        User u = userDao.find(1);
        assertEquals(user1, u);
    }
}
