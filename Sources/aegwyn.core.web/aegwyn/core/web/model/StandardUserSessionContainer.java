/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aegwyn.core.web.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author sunwell
 */
@ApplicationScoped
public class StandardUserSessionContainer implements UserSessionContainer
{
    @Inject
    private WebApplicationContext wac;
    
    private Map<String, UserSession> sessions = new HashMap<> ();
    
    
    @PostConstruct
    public void init() {
        System.out.println ("CONTAINER POSt CONSTRUCT CALLED");
        System.out.println ("WAC: " + wac.toString ());
        System.out.println ("DATABASE: " + wac.getProperty ("database"));
    }
    
    @Override
    public UserSession getSession(String _session, boolean _createNew)
    {
        UserSession session = _session != null ? sessions.get (_session) : null;
        if(session == null && _createNew) {
            session = new UserSession ();
            sessions.put (session.getSessionId (), session);
        }
            return session;
    }
    
    @Override
    public UserSession newSession()
    {
        UserSession session = new UserSession ();
        sessions.put (session.getSessionId (), session);
        return session;
    }
    
    @Override
    public boolean removeSession(String _sessionId) {
//        System.out.println ("_sessionId: " + _sessionId);
        Object obj = sessions.remove (_sessionId);
        return obj != null ? true : false;
    }
    
    /**
     * @param wac the wac to set
     */
    public void setWac (WebApplicationContext wac)
    {
        this.wac = wac;
    }
    
}
