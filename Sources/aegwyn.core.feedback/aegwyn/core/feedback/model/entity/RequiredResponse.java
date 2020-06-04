/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Requiredresponse.java
 *
 * Created on May 26, 2017, 10:56:04 AM
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
@Table(name = "requiredresponse")
@NamedQueries({
    @NamedQuery(name = "RequiredResponse.findAll", query = "SELECT r FROM RequiredResponse r")
    , @NamedQuery(name = "RequiredResponse.findByComplaint", query = "SELECT r FROM RequiredResponse r WHERE r.complaint = :complaint")
    , @NamedQuery(name = "RequiredResponse.findByDuedate", query = "SELECT r FROM RequiredResponse r WHERE r.dueDate = :dueDate")
    , @NamedQuery(name = "RequiredResponse.findByMemo", query = "SELECT r FROM RequiredResponse r WHERE r.memo = :memo")})
@XmlRootElement
public class RequiredResponse implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    private String tenantId;
    
    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "idcomplaint", referencedColumnName = "systemid")
    private Complaint complaint;
   
    @NotNull
    @Column(name = "duedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    
    @Size(max = 2147483647)
    @Column(name = "memo")
    private String memo;
    
    public RequiredResponse ()
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

    public Date getDueDate ()
    {
        return dueDate;
    }

    public void setDueDate (Date duedate)
    {
        this.dueDate = duedate;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public Complaint getFeedback ()
    {
        return complaint;
    }

    public void setComplaint (Complaint _complaint)
    {
        this.complaint = _complaint;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (complaint.getSystemId () != null ? complaint.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequiredResponse)) {
            return false;
        }
        RequiredResponse other = (RequiredResponse) object;
        if ((this.complaint == null && other.complaint != null) || (this.complaint != null && !this.complaint.equals (other.complaint)))
            return false;
        return true;
    }
}
