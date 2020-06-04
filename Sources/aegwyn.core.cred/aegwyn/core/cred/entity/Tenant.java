/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Tenant.java
 *
 * Created on Apr 19, 2017, 2:51:01 PM
 */

package aegwyn.core.cred.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="tenant")
@UuidGenerator(name="UUID")
@NamedQueries ({
    @NamedQuery (name="Tenant.findByTenantId", query="SELECT T FROM Tenant T WHERE T.tenantId = :id"),
    @NamedQuery (name="Tenant.findByEmail", query="SELECT T FROM Tenant T WHERE T.email = :email"),
    @NamedQuery (name="Tenant.findByCompanyName", query="SELECT T FROM Tenant T WHERE T.companyName = :name")
})
public class Tenant 
{
    @Id
    @GeneratedValue(generator="UUID")
    private String tenantId;
    
    private String email;
    
    private String companyName;
    
    @ManyToOne
    @JoinColumn(name="tenantlocation")
    private TenantLocation tenantLocation;
    
    public void  setTenantId(String _id) {
        tenantId = _id;
    }

    /**
     * @return the tenantid
     */
    public String getTenantId ()
    {
        return tenantId;
    }

    /**
     * @return the tenantLocation
     */
    public TenantLocation getTenantLocation ()
    {
        return tenantLocation;
    }

    /**
     * @param tenantNo the tenantLocation to set
     */
    public void setTenantLocation (TenantLocation tenantNo)
    {
        this.tenantLocation = tenantNo;
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
     * @return the companyName
     */
    public String getCompanyName ()
    {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }
}
