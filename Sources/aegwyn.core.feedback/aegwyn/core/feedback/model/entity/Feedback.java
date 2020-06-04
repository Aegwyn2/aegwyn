/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Feedback.java
 *
 * Created on May 26, 2017, 10:56:02 AM
 */

package aegwyn.core.feedback.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "feedback")
@Inheritance (strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findBySystemid", query = "SELECT f FROM Feedback f WHERE f.systemId = :systemId")
    , @NamedQuery(name = "Feedback.findByCustreaction", query = "SELECT f FROM Feedback f WHERE f.custReaction = :custReaction")
    , @NamedQuery(name = "Feedback.findByHasimage", query = "SELECT f FROM Feedback f WHERE f.hasImage = :hasImage")
    , @NamedQuery(name = "Feedback.findByPriority", query = "SELECT f FROM Feedback f WHERE f.priority = :priority")
    , @NamedQuery(name = "Feedback.findByMaplocation", query = "SELECT f FROM Feedback f WHERE f.mapLocation = :mapLocation")
    , @NamedQuery(name = "Feedback.findByIssuedate", query = "SELECT f FROM Feedback f WHERE f.issueDate = :issueDate")
    , @NamedQuery(name = "Feedback.findByEmaillist", query = "SELECT f FROM Feedback f WHERE f.emailList = :emailList")
    , @NamedQuery(name = "Feedback.findByHasaction", query = "SELECT f FROM Feedback f WHERE f.hasAction = :hasAction")
    , @NamedQuery(name = "Feedback.findByFeedbackcontent", query = "SELECT f FROM Feedback f WHERE f.feedbackContent = :feedbackContent")
    , @NamedQuery(name = "Feedback.findBySystemcreatedate", query = "SELECT f FROM Feedback f WHERE f.systemCreateDate = :systemCreateDate")
    , @NamedQuery(name = "Feedback.findByMemo", query = "SELECT f FROM Feedback f WHERE f.memo = :memo")})
@XmlRootElement
public class Feedback implements Serializable 
{

    
    private static final long serialVersionUID = 1L;
    
    private String tenantId;
    
    @Id
    @SequenceGenerator (name = "feedback_systemid_seq", sequenceName = "feedback_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "feedback_systemid_seq" )
    @Column(name = "systemid")
    private Long systemId;
    
    @JoinColumn(name = "idcustomer", referencedColumnName = "systemid")
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "feedback")
    private Response response;
            
    @Column(name = "custreaction")
    private Integer custReaction;
        
    @Column(name = "priority")
    private Integer priority;
    
    @Column(name = "hasimage")
    private Boolean hasImage;
    
    @Column(name = "hasaction")
    private Boolean hasAction;
    
    @Size(max = 255)
    @Column(name = "maplocation")
    private String mapLocation;
    
    @Column(name = "emaillist")
    private String emailList;
            
    @Size(max = 2147483647)
    @Column(name = "feedbackcontent")
    private String feedbackContent;
        
    @Column(name = "memo")
    private String memo;
    
    @Column(name = "issuedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    
    @Column(name = "systemcreatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreateDate;    
    

    public Feedback ()
    {
    }

    public Feedback (Long systemid)
    {
        this.systemId = systemid;
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

    public Integer getCustReaction ()
    {
        return custReaction;
    }

    public void setCustReaction (Integer custreaction)
    {
        this.custReaction = custreaction;
    }

    public Boolean getHasImage ()
    {
        return hasImage;
    }

    public void setHasImage (Boolean hasimage)
    {
        this.hasImage = hasimage;
    }

    public Integer getPriority ()
    {
        return priority;
    }

    public void setPriority (Integer priority)
    {
        this.priority = priority;
    }

    public String getMapLocation ()
    {
        return mapLocation;
    }

    public void setMapLocation (String maplocation)
    {
        this.mapLocation = maplocation;
    }

    public Date getIssueDate ()
    {
        return issueDate;
    }

    public void setIssueDate (Date issuedate)
    {
        this.issueDate = issuedate;
    }

    public String getEmailList ()
    {
        return emailList;
    }

    public void setEmailList (String emaillist)
    {
        this.emailList = emaillist;
    }

    public Boolean getHasAction ()
    {
        return hasAction;
    }

    public void setHasAction (Boolean hasaction)
    {
        this.hasAction = hasaction;
    }

    public String getFeedbackContent ()
    {
        return feedbackContent;
    }

    public void setFeedbackContent (String feedbackcontent)
    {
        this.feedbackContent = feedbackcontent;
    }

    public Date getSystemCreateDate ()
    {
        return systemCreateDate;
    }

    public void setSystemCreateDate (Date systemcreatedate)
    {
        this.systemCreateDate = systemcreatedate;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer idcustomer)
    {
        this.customer = idcustomer;
    }

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

//    public RequiredResponse getRequiredResponse ()
//    {
//        return requiredResponse;
//    }
//
//    public void setRequiredResponse (RequiredResponse requiredresponse)
//    {
//        this.requiredResponse = requiredresponse;
//    }
    
//    /**
//     * @return the feedbackCall
//     */
//    public Boolean isFeedbackCall ()
//    {
//        return feedbackCall;
//    }
//
//    /**
//     * @param feedbackCall the feedbackCall to set
//     */
//    public void setFeedbackCall (Boolean feedbackCall)
//    {
//        this.feedbackCall = feedbackCall;
//    }
//
//    /**
//     * @return the urgentCall
//     */
//    public Boolean isUrgentCall ()
//    {
//        return urgentCall;
//    }
//
//    /**
//     * @param urgentCall the urgentCall to set
//     */
//    public void setUrgentCall (Boolean urgentCall)
//    {
//        this.urgentCall = urgentCall;
//    }

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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

//    @Override
//    public String toString ()
//    {
//        return "aegwyn.core.feedback.model.entity.Feedback[ systemid=" + systemId + " ]";
//    }

}
