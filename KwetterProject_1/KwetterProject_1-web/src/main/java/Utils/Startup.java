/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Domain.Kweet;
import Domain.User;
import Exceptions.KwetterException;
import Exceptions.UserException;
import Service.KwetterService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author jeffrey
 */
@javax.ejb.Startup
@Singleton

public class Startup {

    @Inject
    private KwetterService service;

    @PostConstruct
    private void initData() {

        try {
            this.createData();
        } catch (KwetterException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createData() throws KwetterException {
        User u = new User("Jeff", "Tilburg.nl");
        User u1 = new User("Frankie", "Frankie.nl");
        User u2 = new User("Bob", "Bob.nl");
        User u3 = new User("Goku", "Goku.nl");

        Kweet k1 = new Kweet("Yo @Bob #slechtekweet", u);
        Kweet k2 = new Kweet("yo2", u);
        Kweet k3 = new Kweet("yo3", u);
        Kweet k4 = new Kweet("yo4", u);
        Kweet k5 = new Kweet("yo5", u);
        Kweet k6 = new Kweet("yo6", u);
        Kweet k7 = new Kweet("yo7", u);
        Kweet k8 = new Kweet("yo8", u);
        Kweet k9 = new Kweet("yo9", u);
        Kweet k10 = new Kweet("yo10", u);
        Kweet k11 = new Kweet("yo11", u);
        service.createKweet(k1);
        service.createKweet(k2);
        service.createKweet(k3);
        service.createKweet(k4);
        service.createKweet(k5);
        service.createKweet(k6);
        service.createKweet(k7);
        service.createKweet(k8);
        service.createKweet(k9);
        service.createKweet(k10);
        service.createKweet(k11);
//        u1.addKweet(k2);
//        u1.addKweet(k1);

        try {
            service.registerUser(u);
            service.registerUser(u1);
            service.registerUser(u2);
            service.registerUser(u3);

            service.followUser(u2, u);
            // service.followUser(u, u2);
            service.followUser(u3, u);
            service.followUser(u, u2);
            service.followUser(u3, u2);
        } catch (UserException e) {
            e.printStackTrace();
            
        }
        
    }
}
