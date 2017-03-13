package Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. testtt1
 */
import Dao.KwetterDAO;
import Dao.UserDAO;
import Domain.Kweet;
import Domain.User;
import Service.KwetterService;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author jeffrey
 */
@RunWith(MockitoJUnitRunner.class)
public class KwetterServiceTest {
    
    
    private KwetterService service;
    @Mock
    private KwetterDAO kwetterDAO;
    @Mock 
    private UserDAO userDAO;

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
        service.setuserDAO(userDAO);
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
    public void testFollowUser() throws Exception {
        User user1 = new User("User", "www.user1.org");
        service.registerUser(user1);
        User user2 = new User("User", "www.user2.org");
        service.registerUser(user2);
        service.followUser(user1, user2);
        Mockito.verify(userDAO, Mockito.times(1)).updateUser(user1);
    }
//
    @Test
    public void testUnfollowUser() throws Exception {
        User user1 = new User("User", "www.user1.org");
        service.registerUser(user1);
        User user2 = new User("User", "www.user2.org");
        service.registerUser(user2);
        service.unfollowUser(user1, user2);
        Mockito.verify(userDAO, Mockito.times(1)).updateUser(user1);
    }
//
    @Test
    public void testGetFollowers() throws Exception {
        User user1 = new User("User", "www.user1.org");
        service.registerUser(user1);
        User user2 = new User("User2", "www.user2.org");
        service.registerUser(user2);
        service.followUser(user1, user2);
        service.getFollowers("User");
        Mockito.verify(userDAO, Mockito.times(1)).getFollowers("User");
    }

    @Test
    public void testCreateKweet() throws Exception {
        User user1 = new User("User", "www.user1.org");
        service.registerUser(user1);
        Kweet kweet = new Kweet("test", user1);
        service.createKweet(kweet);
        Mockito.verify(kwetterDAO, Mockito.times(1)).createKweet(kweet);
    }
}
