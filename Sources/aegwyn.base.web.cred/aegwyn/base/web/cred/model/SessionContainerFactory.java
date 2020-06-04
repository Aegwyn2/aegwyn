/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * SessionContainerFactory.java
 *
 * Created on May 29, 2017, 12:26:08 PM
 */

package aegwyn.base.web.cred.model;

import aegwyn.core.web.model.StandardUserSessionContainer;
import aegwyn.core.web.model.UserSessionContainer;
import aegwyn.core.web.model.WebApplicationContext;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
/**
 *
 * @author Benny
 */
@ApplicationScoped
public class SessionContainerFactory 
{
    @Inject
    WebApplicationContext wca;
    
    @Produces
    @ApplicationScoped
    public UserSessionContainer getUserSessionContainer() {
        System.out.println ("FACTORY CALLED");
        String credUrl = wca.getProperty ("credURL");
        String credPath = wca.getProperty ("credPath");
        if(credUrl != null && credPath != null) {
            RemoteUserSessionContainer usc = new RemoteUserSessionContainer ();
            usc.setCredURL (credUrl);
            usc.setCredPath (credPath);
            return usc;
        }
        else
            return new StandardUserSessionContainer ();
    }
}
