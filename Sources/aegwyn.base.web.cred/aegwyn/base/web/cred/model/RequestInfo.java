/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * RequestInfo.java
 *
 * Created on May 2, 2017, 3:09:28 PM
 */

package aegwyn.base.web.cred.model;

import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.cred.entity.Tenant;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Benny
 */
@RequestScoped
public class RequestInfo 
{
    private Tenant currentTenant;
    private RegisteredUser currentUser;

    /**
     * @return the currentTenant
     */
    public Tenant getCurrentTenant ()
    {
        return currentTenant;
    }

    /**
     * @param currentTenant the currentTenant to set
     */
    public void setCurrentTenant (Tenant currentTenant)
    {
        this.currentTenant = currentTenant;
    }

    /**
     * @return the currentUser
     */
    public RegisteredUser getCurrentUser ()
    {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser (RegisteredUser currentUser)
    {
        this.currentUser = currentUser;
    }
}
