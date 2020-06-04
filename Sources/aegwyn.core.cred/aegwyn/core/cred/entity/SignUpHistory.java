/**
 * SignUpHistory.java
 *
 * Created on Mar 22, 2017, 2:57:39 PM
 */
package aegwyn.core.cred.entity;

//import control.ServletCtx;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="signuphistory")
@NamedQueries ({
    @NamedQuery (name="SignUpHistory.findByEmail", query="SELECT s FROM SignUpHistory s WHERE s.email = :email")
})
public class SignUpHistory implements Serializable
{

    @Id
    @SequenceGenerator (name = "signuphistory_systemid_seq", sequenceName = "signuphistory_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "signuphistory_systemid_seq" )
    @Column (name = "systemid", insertable = false)
    private Long systemId;
    
//    @GeneratedValue(generator="UUID")
    private String tenantId; 
    
    @Column (name="email", unique=true)
    private String email;
    
    @Column(name="clientui")
    private String clientUI;    // firefox-desktop, chrome-android, 
    
    @Column(name="registerip")
    private String registerFromIP;
    
    @Column(name="registertime")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar registerTime;

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
     * @return the clientUI
     */
    public String getClientUI ()
    {
        return clientUI;
    }

    /**
     * @param clientUI the clientUI to set
     */
    public void setClientUI (String clientUI)
    {
        this.clientUI = clientUI;
    }

    /**
     * @return the registerFromIP
     */
    public String getRegisterFromIP ()
    {
        return registerFromIP;
    }

    /**
     * @param registerFromIP the registerFromIP to set
     */
    public void setRegisterFromIP (String registerFromIP)
    {
        this.registerFromIP = registerFromIP;
    }

    /**
     * @return the registerTime
     */
    public Calendar getRegisterTime ()
    {
        return registerTime;
    }

    /**
     * @param registerTime the registerTime to set
     */
    public void setRegisterTime (Calendar registerTime)
    {
        this.registerTime = registerTime;
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
}
