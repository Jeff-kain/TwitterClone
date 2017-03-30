/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Batch;

import Domain.Kweet;
import Domain.User;
import Exceptions.UserException;
import Service.KwetterService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author jeffrey
 */
@Dependent
@Named("KweetProcessor")
public class KweetProcessor implements ItemProcessor {

    @Inject
    private KwetterService service;

    @Override
    public Object processItem(Object item) throws Exception {
        InputKweet inputkweet = (InputKweet) item;
        Kweet kweet = new Kweet();
        kweet.setContent(inputkweet.content);
        User user = service.findUser(inputkweet.user);
        if (user == null) {
            user = new User(inputkweet.user, "");
            try {
                service.registerUser(user);
            } catch (UserException ex) {
                Logger.getLogger(KweetWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        kweet.setUser(user);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(inputkweet.postDate);
        GregorianCalendar Cal = new GregorianCalendar();
        Cal.setTime(date);
        kweet.setPostDate(Cal);
        return kweet;
    }
}
