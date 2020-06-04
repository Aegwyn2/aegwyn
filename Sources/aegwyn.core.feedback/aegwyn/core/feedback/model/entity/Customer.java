/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Customer.java
 *
 * Created on May 26, 2017, 10:56:04 AM
 */

package aegwyn.core.feedback.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findBySystemid", query = "SELECT c FROM Customer c WHERE c.systemId = :systemId")
    , @NamedQuery(name = "Customer.findByFullname", query = "SELECT c FROM Customer c WHERE c.fullName = :fullName")
    , @NamedQuery(name = "Customer.findByAddrnation", query = "SELECT c FROM Customer c WHERE c.addrNation = :addrNation")
    , @NamedQuery(name = "Customer.findByAddrstate", query = "SELECT c FROM Customer c WHERE c.addrState = :addrState")
    , @NamedQuery(name = "Customer.findByAddrcity", query = "SELECT c FROM Customer c WHERE c.addrCity = :addrCity")
    , @NamedQuery(name = "Customer.findByAddrstreet", query = "SELECT c FROM Customer c WHERE c.addrStreet = :addrStreet")
    , @NamedQuery(name = "Customer.findByAddrphone", query = "SELECT c FROM Customer c WHERE c.addrPhone = :addrPhone")
    , @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")})
@XmlRootElement
public class Customer implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    private String tenantId;
    
    @Id
    @SequenceGenerator (name = "customer_systemid_seq", sequenceName = "customer_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "customer_systemid_seq" )
    @Column(name = "systemid")
    private Long systemId;
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullname")
    private String fullName;
   
    @Size(max = 64)
    @Column(name = "addrnation")
    private String addrNation;
    
    @Size(max = 64)
    @Column(name = "addrstate")
    private String addrState;
    
    @Size(max = 64)
    @Column(name = "addrcity")
    private String addrCity;
    
    @Size(max = 64)
    @Column(name = "addrstreet")
    private String addrStreet;
    
    @Size(max = 64)
    @Column(name = "addrphone")
    private String addrPhone;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(name = "email")
    private String email;
    
    @Column(name="gender")
    private Integer gender;
    
    @Column(name="age")
    private Integer age;
    
    public Customer ()
    {
    }

    public Customer (Long systemid)
    {
        this.systemId = systemid;
    }

    public Customer (Long systemid, String fullname)
    {
        this.systemId = systemid;
        this.fullName = fullname;
    }
    
    /**
     * @return the tenantId
     */
    public String getTenantId ()
    {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    public Long getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (Long systemid)
    {
        this.systemId = systemid;
    }

    public String getFullName ()
    {
        return fullName;
    }

    public void setFullName (String fullname)
    {
        this.fullName = fullname;
    }

    public String getAddrNation ()
    {
        return addrNation;
    }

    public void setAddrNation (String addrnation)
    {
        this.addrNation = addrnation;
    }

    public String getAddrState ()
    {
        return addrState;
    }

    public void setAddrState (String addrstate)
    {
        this.addrState = addrstate;
    }

    public String getAddrCity ()
    {
        return addrCity;
    }

    public void setAddrCity (String addrcity)
    {
        this.addrCity = addrcity;
    }

    public String getAddrStreet ()
    {
        return addrStreet;
    }

    public void setAddrStreet (String addrstreet)
    {
        this.addrStreet = addrstreet;
    }

    public String getAddrPhone ()
    {
        return addrPhone;
    }

    public void setAddrPhone (String addrphone)
    {
        this.addrPhone = addrphone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }
    
    /**
     * @return the gender
     */
    public Integer getGender ()
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender (Integer gender)
    {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public Integer getAge ()
    {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge (Integer age)
    {
        this.age = age;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (systemId != null ? systemId.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "aegwyn.core.feedback.model.entity.Customer[ systemid=" + systemId + " ]";
    }

}
