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
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jeffrey
 */
@Dependent
@Named("KweetWriter")
public class KweetWriter implements ItemWriter{

    @Inject
    KwetterService service;
  
    @Override
    public void close() throws Exception {
    }

    @Override
    public void writeItems(List<Object> items) {

        for (Object item : items) {
            Kweet k = (Kweet) item;
            service.createKweet(k);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
    }

}
