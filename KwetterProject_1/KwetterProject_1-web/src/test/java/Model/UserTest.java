package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.KwetterDAO;
import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author jeffrey
 */
public class UserTest {

    private KwetterService service;
    @Mock
    private KwetterDAO kwetterDAO;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new KwetterService();
        service.setKwetterDAO(kwetterDAO);
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
    public void addFollowTest() {
        User user1 = new User("User1", "www.user1.org");
        User user2 = new User("User2", "www.user2.org");
        user1.addFollow(user1);
    }
//
//    @Test
//    public void removeFollow(User follow) {
//        User user1 = new User("User1", "www.user1.org");
//        User user2 = new User("User2", "www.user2.org");
//        user1.addFollow(user1);
//        user1.removeFollow(user2);
//    }
//
//    @Test
//    public void addKweet(Kweet kweet) {
//        User user1 = new User("User1", "www.user1.org");
//        Kweet kweet1 = new Kweet("kweet1", user1);
//        user1.addKweet(kweet1);
//    }
}
