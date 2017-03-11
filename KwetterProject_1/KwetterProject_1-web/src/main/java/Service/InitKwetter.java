/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.Kweet;
import Domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author jeffrey
 */
@javax.ejb.Startup
@Singleton

public class InitKwetter {

    @Inject
    private KwetterService service;

    @PostConstruct
    private void initData() {
        User u = new User("Jeff", "Tilburg.nl");
        User u1 = new User("Frankie", "Frankie.nl");
        User u2 = new User("Bob", "Bob.nl");
        User u3 = new User("Goku", "Goku.nl");

        Kweet k1 = new Kweet("Yo", u);
        Kweet k2 = new Kweet("yoyo", u);
        service.createKweet(k2);
        service.createKweet(k1);
//        u1.addKweet(k2);
//        u1.addKweet(k1);

        service.registerUser(u);
        service.registerUser(u1);
        service.registerUser(u2);
        service.registerUser(u3);
    }
}
