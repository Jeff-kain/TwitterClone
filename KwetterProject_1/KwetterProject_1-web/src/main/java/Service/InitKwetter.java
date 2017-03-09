/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
        service.registerUser(u);
    }
}
