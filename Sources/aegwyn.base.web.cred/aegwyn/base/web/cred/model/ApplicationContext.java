/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * WebRuntime.java
 *
 * Created on May 2, 2017, 3:06:10 PM
 */

package aegwyn.base.web.cred.model;

import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.cred.entity.Tenant;
import aegwyn.core.web.model.StandardWebConstant;
import aegwyn.core.web.model.WebApplicationContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author Benny
 */
@ApplicationScoped
public class ApplicationContext 
{
//    private final static String persistenceJNDIPath = "java:comp/env/persistence/LOCATION/";
    
    @Inject
    private RequestInfo requestInfo;
    
    @Inject
    private WebApplicationContext wapc;

    /**
     * @return the requestInfo
     */
//    public RequestInfo getRequestInfo ()
//    {
//        return requestInfo;
//    }
    
    public void setCurrentTenant(Tenant _t) 
    {
        requestInfo.setCurrentTenant (_t);
        wapc.getWebRequestInfo ().setPersistenceJNDILocation (StandardWebConstant.PERSISTENCE_JNDI_PATH + _t.getTenantLocation ().getNo ());
    }
    
    public Tenant getCurrentTenant() {
        return requestInfo.getCurrentTenant ();
    }
    
    public void setCurrentUser(RegisteredUser _user) 
    {
        requestInfo.setCurrentUser (_user);
        requestInfo.setCurrentTenant (_user.getTenant ());
        wapc.getWebRequestInfo ().setPersistenceJNDILocation (StandardWebConstant.PERSISTENCE_JNDI_PATH  + _user.getTenant ().getTenantLocation ().getNo ());
    }
    
    public RegisteredUser getCurrentUser() {
        return requestInfo.getCurrentUser ();
    }
    
    
}
