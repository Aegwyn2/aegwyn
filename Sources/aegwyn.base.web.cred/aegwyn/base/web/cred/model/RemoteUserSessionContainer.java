/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * RemoteUserSessionContainer.java
 *
 * Created on May 29, 2017, 11:40:33 AM
 */

package aegwyn.base.web.cred.model;

import aegwyn.base.web.cred.dto.UserDTO;
import aegwyn.core.cred.model.entity.RegisteredUser;
import aegwyn.core.cred.model.entity.Tenant;
import aegwyn.core.cred.model.entity.TenantLocation;
import aegwyn.core.web.model.UserSession;
import aegwyn.core.web.model.UserSessionContainer;
import aegwyn.core.web.model.WebApplicationContext;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Benny
 */
@ApplicationScoped
@RemoteSession
public class RemoteUserSessionContainer implements UserSessionContainer
{
    @EJB
    WebApplicationContext wac;
    
    private Map<String, UserSession> sessions = new HashMap<> ();

    private String credURL;
    private String credPath ;
    
    @PostConstruct
    public void init() {
        setCredURL (wac.getProperty ("credURL"));
        setCredPath (wac.getProperty ("credPath"));
        System.out.println ("REMOTE USER SESSION CALLED");
    }
    
    @Override
    public UserSession newSession ()
    { 
        throw new UnsupportedOperationException ("NOT SUPPORTED YET");
    }

    @Override
    public UserSession getSession (String _sessionId, boolean _createNew)
    {
        UserSession us = sessions.get (_sessionId);
        if(us == null)
        {
            WebTarget target ;
            Client client = ClientBuilder.newClient ();
            target = client.target (getCredURL ()).path (getCredPath ()).queryParam ("sessionString", _sessionId);
            System.out.println ("URI; " + target.getUri ().toString ());
            Invocation.Builder request = target.request (MediaType.APPLICATION_JSON);
            UserDTO result = request.get (UserDTO.class);
            if(result.getErrorMessage () != null && result.getErrorMessage ().length () > 0)
                return null;

            us = new UserSession ();
            us.setSessionName ("Login");
            us.setSessionId (result.getSessionString ());
            us.setLastActivity (Calendar.getInstance ());

            TenantLocation tlo = new TenantLocation ();
            tlo.setNo (result.getLocationNo ());
            tlo.setAmount (result.getLocationTenantAmount ());

            Tenant tenant = new Tenant ();
            tenant.setTenantId (result.getTenantId ());
            tenant.setCompanyName (result.getCompanyName ());
            tenant.setEmail (result.getEmail ());
            tenant.setTenantLocation (tlo);

            RegisteredUser user = new RegisteredUser ();
            user.setEmail (result.getEmail ());
            user.setTenant (tenant);

            us.addObject ("user", user);
            us.addObject ("tenant", tenant);
        }
        return us;
    }
    
    @Override
    public boolean removeSession(String _sessionId) {
        throw new UnsupportedOperationException ("REMOVE SESSION METHOD IS NOT SUPPORTED");
    }
    
    /**
     * @return the credURL
     */
    public String getCredURL ()
    {
        return credURL;
    }

    /**
     * @param credURL the credURL to set
     */
    public void setCredURL (String credURL)
    {
        this.credURL = credURL;
    }

    /**
     * @return the credPath
     */
    public String getCredPath ()
    {
        return credPath;
    }

    /**
     * @param credPath the credPath to set
     */
    public void setCredPath (String credPath)
    {
        this.credPath = credPath;
    }
}
