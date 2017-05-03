/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Startup;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 *
 * @author jeffrey
 */
@Startup
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/kwettergo"),
})
public class MessageBean implements MessageListener {
 
 
    @Override
    public void onMessage(Message message) {
 
        try
        {
            TextMessage textMessage = (TextMessage) message;
            //Whatever je wil hier met je bericht etc.
            System.out.println(textMessage.getText());
        }
        catch (Exception e){e.printStackTrace();}
    }
}
