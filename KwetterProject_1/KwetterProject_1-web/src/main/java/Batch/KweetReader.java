/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Batch;

import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;

/**
 *
 * @author jeffrey
 */
@Dependent
@Named("KweetReader")
public class KweetReader implements ItemReader {

    @Inject
    private JobContext jobContext;

    private String fileName;

    private JsonParser parser;

    private Checkpoint checkpoint;

    private boolean start;

    @Override
    public void open(Serializable srlzbl) throws Exception {
        if (checkpoint == null) {
            this.checkpoint = new Checkpoint();
        } else {
            this.checkpoint = (Checkpoint) checkpoint;
        }

        fileName = jobContext.getProperties().getProperty("input_file");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream iStream = classLoader.getResourceAsStream(fileName);

        parser = Json.createParser(iStream);

        start = false;
        for (long i = 0; i < this.checkpoint.getCount(); ++i) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.START_ARRAY) {
                start = true;
            }
        }
    }

    @Override
    public void close() throws Exception {
        parser.close();
    }

    @Override
    public Object readItem() throws Exception {
        boolean itemFound = false;
        InputKweet item = new InputKweet();

        System.out.println("Read Item");

        while (itemFound == false
                && parser.hasNext() == true) {
            JsonParser.Event event = parser.next();
            checkpoint.eventHappened();

            switch (event) {
                case START_ARRAY:
                    start = true;
                    break;
                case VALUE_STRING:
                    if (start == true) {
                        if (item.user == null) {
                            item.user = parser.getString();
                        } else if (item.content == null) {
                            item.content = parser.getString();
                        } else if (item.postDate == null) {
                            item.postDate = parser.getString();
                            itemFound = true;
                        }
                    }
                    break;
                case END_ARRAY:
                    item = null;
                    break;
            }
        }
        return item;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new Checkpoint();
    }
}
