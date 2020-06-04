/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * RegisteredUser.java
 *
 * Created on Apr 25, 2017, 11:57:26 AM
 */

package aegwyn.core.cred.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="registereduser")
@NamedQueries ({
    @NamedQuery (name="RegisteredUser.findByEmail", query="SELECT R FROM RegisteredUser R WHERE R.email = :email"),
    @NamedQuery (name="RegisteredUser.findByTenant", query="SELECT R FROM RegisteredUser R WHERE R.tenant = :tenant"),
    @NamedQuery (name="RegisteredUser.findByEmailAndTenant", query="SELECT R FROM RegisteredUser R WHERE R.email = :email AND R.tenant = :tenant")
})
@IdClass(RegisteredUserPK.class)
public class RegisteredUser 
{
    @Id
    private String email;
    
    @Id
    @ManyToOne
    @JoinColumn(name="tenant")
    private Tenant tenant;
    
    private boolean created = false;
    
     /**
     * @return the tenant
     */
    public Tenant getTenant ()
    {
        return tenant;
    }

    /**
     * @param tenant the tenant to set
     */
    public void setTenant (Tenant tenant)
    {
        this.tenant = tenant;
    }

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
     * @return the created
     */
    public boolean isCreated ()
    {
        return created;
    }

    /**
     * @param isCreated the created to set
     */
    public void setCreated (boolean isCreated)
    {
        this.created = isCreated;
    }
}
