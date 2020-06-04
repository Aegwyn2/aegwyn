/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Response.java
 *
 * Created on May 26, 2017, 10:56:03 AM
 */

package aegwyn.core.feedback.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "response")
@NamedQueries({
    @NamedQuery(name = "Response.findAll", query = "SELECT r FROM Response r")
    , @NamedQuery(name = "Response.findByIdfeedback", query = "SELECT r FROM Response r WHERE r.feedback = :feedback")
    , @NamedQuery(name = "Response.findByResponsedate", query = "SELECT r FROM Response r WHERE r.responseDate = :responseDate")
    , @NamedQuery(name = "Response.findByMemo", query = "SELECT r FROM Response r WHERE r.memo = :memo")})
@XmlRootElement
public class Response implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    private String tenantId;
   
    @Id
    @JoinColumn(name = "idfeedback")
    @OneToOne(optional = false)
    private Feedback feedback;
    
    @NotNull
    @Column(name = "responsedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    
    @Size(max = 2147483647)
    @Column(name = "memo")
    private String memo;
    
    public Response ()
    {
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

    public Date getResponseDate ()
    {
        return responseDate;
    }

    public void setResponseDate (Date responsedate)
    {
        this.responseDate = responsedate;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public Feedback getFeedback ()
    {
        return feedback;
    }

    public void setFeedback (Feedback feedback)
    {
        this.feedback = feedback;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (feedback != null ? feedback.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.feedback == null && other.feedback != null) || (this.feedback != null && !this.feedback.equals (other.feedback)))
            return false;
        return true;
    }

}
