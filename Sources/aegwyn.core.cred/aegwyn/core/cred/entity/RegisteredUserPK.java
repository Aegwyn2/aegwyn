/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * RegisteredUserPK.java
 *
 * Created on May 19, 2017, 2:18:10 PM
 */

package aegwyn.core.cred.entity;

import java.io.Serializable;

/**
 *
 * @author Benny
 */
public class RegisteredUserPK implements Serializable
{
    private String email;
    private String tenant;
        
    /**
     * @return the email
     */
    public String getEmail ()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail (String email)
    {
        this.email = email;
    }

    /**
     * @return the tenant
     */
    public String getTenant ()
    {
        return tenant;
    }

    /**
     * @param tenant the tenant to set
     */
    public void setTenant (String tenant)
    {
        this.tenant = tenant;
    }
    
    @Override
    public boolean equals(Object _o) {
        if(! (_o instanceof RegisteredUserPK))
            return false;
        RegisteredUserPK pk = (RegisteredUserPK) _o;
        if(!pk.getEmail ().equals (email))
            return false;
        if(!pk.getTenant ().equals (tenant))
            return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = getEmail ().hashCode ();
        hash += getTenant ().hashCode ();
        return hash;
    }
}
