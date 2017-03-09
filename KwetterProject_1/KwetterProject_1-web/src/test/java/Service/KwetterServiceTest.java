package Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. testtt1
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
import org.mockito.Mockito;

/**
 *
 * @author jeffrey
 */
public class KwetterServiceTest {

    private KwetterService service;
    @Mock
    private KwetterDAO kwetterDAO;

    public KwetterServiceTest() {

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
    public void sampleTest() {
        int a = 1;
        int b = 2;
        assertTrue(a + b == 3);
    }

//    @Test
//    public void testFollowUser() throws Exception {
//        User user1 = new User(124L, "User", "www.user1.org");
//        service.registerUser(user1);
//        User user2 = new User(123L, "User", "www.user2.org");
//        service.registerUser(user2);
//        service.followUser(user1, user2);
//        Mockito.verify(kwetterDAO, Mockito.times(1)).updateUser(user1);
//    }
//
//    @Test
//    public void testUnfollowUser() throws Exception {
//        User user1 = new User(123L, "User", "www.user1.org");
//        service.registerUser(user1);
//        User user2 = new User(124L, "User", "www.user2.org");
//        service.registerUser(user2);
//        service.unfollowUser(user1, user2);
//        Mockito.verify(kwetterDAO, Mockito.times(1)).updateUser(user1);
//    }
//
//    @Test
//    public void testGetFollowers() throws Exception {
//        User user1 = new User(123L, "User", "www.user1.org");
//        service.registerUser(user1);
//        User user2 = new User(124L, "User", "www.user2.org");
//        service.registerUser(user2);
//        service.followUser(user1, user2);
//        service.getFollowers(user2);
//        Mockito.verify(kwetterDAO, Mockito.times(1)).getFollowers(user2);
//    }
//
//    @Test
//    public void testCreateKweet() throws Exception {
//        User user1 = new User(123L, "User", "www.user1.org");
//        service.registerUser(user1);
//        Kweet kweet = new Kweet("test", user1);
//        service.createKweet(kweet);
//        Mockito.verify(kwetterDAO, Mockito.times(1)).createKweet(kweet);
//    }
}
