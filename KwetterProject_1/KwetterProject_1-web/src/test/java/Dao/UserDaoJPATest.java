/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        userDao = new UserDaoJPA();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterProject_KwetterProject_1-web_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        userDao.setEm(em);
        dbc = new DatabaseCleaner(em);
    }

    private EntityManager em;
    private UserDaoJPA userDao;
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
        dbc.clean();
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
//    public void testCreateUser() throws Exception {
//        Assert.assertTrue(true);
//        user1 = new User("User", "www.user1.org");
//        userDao.create(user1);
//        User u = userDao.find(user1);
//        assertEquals(user1, u);
//    }
}
