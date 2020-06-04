/**
 * UserCred.java
 *
 * Created on Mar 20, 2017, 3:01:53 PM
 */
package aegwyn.core.cred.entity;

//import control.ServletCtx;
//import control.util.Util;
import java.io.Serializable;
import java.util.logging.Level;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity
@Table (name="contact")
@Inheritance (strategy = InheritanceType.JOINED)
@NamedQueries ({
    @NamedQuery (name="Contact.findBySystemId", query="SELECT c FROM Contact c WHERE c.systemId = :sysid"),
    @NamedQuery (name="Contact.findByCredId", query="SELECT c FROM Contact c WHERE c.credential.systemId = :credid"),
    @NamedQuery (name="Contact.findByEmail", query="SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery (name="Contact.findByEmailAndTenantId", query="SELECT c FROM Contact c WHERE c.email = :email ANd c.tenantId=:tenantId"),
    @NamedQuery (name="Contact.findAll", query="SELECT c FROM Contact c")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable
{
    @Id
    @SequenceGenerator (name = "contact_systemid_seq", sequenceName = "contact_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "contact_systemid_seq" )
    @Column (name = "systemid", insertable = false)
    private Long systemId;
    
    private String tenantId; 
    
    @Column(name="firstname")
    private String firstName;
    
    @Column(name="lastname")
    private String lastName;
    
    @Column (name="email", nullable=false)
    private String email;
    
    
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cred")
    private UserCredential credential;
    
    /**
     * @return the systemId
     */
    public Long getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (Long systemId)
    {
        this.systemId = systemId;
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

    /**
     * @return the firstName
     */
    public String getFirstName ()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName ()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getEmail ()
    {
        return email;
    }
    
    public void setEmail (String _s)
    {
        email = _s;
    }

    /**
     * @return the cred
     */
    public UserCredential getCredential ()
    {
        return credential;
    }

    /**
     * @param cred the cred to set
     */
    public void setCredentials (UserCredential cred)
    {
        this.credential = cred;
    }

   
}
