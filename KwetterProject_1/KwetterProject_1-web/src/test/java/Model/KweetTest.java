package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test11dste
 */

import Dao.KwetterDAO;
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
public class KweetTest {

    private KwetterService service;
    @Mock
    private KwetterDAO kwetterDAO;

    public KweetTest() {
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
      //  service.setKwetterDAO(kwetterDAO);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
