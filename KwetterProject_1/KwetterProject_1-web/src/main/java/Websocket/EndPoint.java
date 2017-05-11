/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websocket;

import Bean.LoggedUserBean;
import Domain.Kweet;
import Domain.User;
import Websocket.HttpSessionProvider;
import Websocket.MessageDecoder;
import Websocket.MessageEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import Websocket.EchoBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author jgeenen
 */
@ServerEndpoint(
        value = "/echo-socket",
        encoders = MessageEncoder.class,
        decoders = MessageDecoder.class,
        configurator = HttpSessionProvider.class
)
public class EndPoint {

    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());

    /**
     * stock glassfish 4.0 doesn't support dependency injection in Endpoints,
     * which is a bug. Hence the EJB lookup.
     */
    @Inject
    private EchoBean delegate;

    private HttpSession httpSession;

    private static Set sessions = new HashSet();

    @OnOpen
    public void onOpen(EndpointConfig endpointConfig, Session session) {
        this.httpSession = HttpSessionProvider.provide(endpointConfig);
        this.sessions.add(session);
        LOG.log(Level.INFO, "onOpen: endpointConfig: {0}, session: {1}", new Object[]{endpointConfig, session});
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        LOG.log(Level.INFO, "received message with text: {0}", message);
        for (Session s : session.getOpenSessions()) {
            delegate.send(s, message, 0, 1000, 1.2);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try {
            httpSession.invalidate();
        } catch (IllegalStateException ise) {
            //swallow: httpSession allready expired
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.log(
                Level.WARNING,
                new StringBuilder("an error occured for session ").append(session).toString(),
                throwable
        );
    }

    public Set<Session> getSession() {
        return sessions;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

}
