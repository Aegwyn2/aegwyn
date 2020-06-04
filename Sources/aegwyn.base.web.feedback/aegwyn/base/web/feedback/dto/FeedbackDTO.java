/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FeedbackDTO.java
 *
 * Created on Jun 19, 2017, 2:46:09 PM
 */

package aegwyn.base.web.feedback.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.feedback.model.entity.Customer;
import aegwyn.core.feedback.model.entity.Feedback;
import aegwyn.core.feedback.model.entity.RequiredResponse;
import aegwyn.core.feedback.model.entity.Response;
import aegwyn.core.web.util.DateTimeAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FeedbackDTO extends StandardDTO
{
    
    private Long systemId;
    
    private Long idBoard;
    
    private Integer custReaction;
    
    private Integer priority;
    
    private Boolean hasImage;
    
    private Boolean hasAction;
    
    private String mapLocation;
    
    private String emailList;
    
    private String feedbackContent;
        
    private String memo;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date issueDate;
    
    private Long custId;
    
    private String fullName;
    
    private String addrNation;
    
    private String addrState;
    
    private String addrCity;
    
    private String addrStreet;
    
    private String addrPhone;
    
    private String email;
    
    private Integer gender;
    
    private Integer age;
    
    
    
  
    

    public FeedbackDTO ()
    {
    }
    
    public void setData(Feedback _feedback) {
        setTenantId (_feedback.getTenantId ());
        systemId = _feedback.getSystemId ();
        custReaction = _feedback.getCustReaction ();
        priority = _feedback.getPriority ();
        hasImage = _feedback.getHasImage ();
        hasAction = _feedback.getHasAction ();
        mapLocation = _feedback.getMapLocation ();
        emailList = _feedback.getEmailList ();
        feedbackContent = _feedback.getFeedbackContent ();
        issueDate = _feedback.getIssueDate ();
        memo= _feedback.getMemo ();
        
        Customer cust = _feedback.getCustomer ();
        
        if(cust != null) {
            setCustId (cust.getSystemId ());
            setFullName (cust.getFullName ());
            setAddrNation (cust.getAddrNation ());
            setAddrState (cust.getAddrState ());
            setAddrCity (cust.getAddrCity ());
            setAddrStreet (cust.getAddrStreet ());
            setAddrPhone (cust.getAddrPhone ());
            setEmail (cust.getEmail ());
            setGender (cust.getGender ());
            setAge (cust.getAge ());
        }
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
    
//    public Boolean getFeedbackCall ()
//    {
//        return feedbackCall;
//    }
//    
//    public Boolean getUrgentCall ()
//    {
//        return urgentCall;
//    }

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

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }
   
    /**
     * @return the feedbackCall
     */
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
    
    /**
     * @return the custId
     */
    public Long getCustId ()
    {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId (Long custId)
    {
        this.custId = custId;
    }

    /**
     * @return the fullName
     */
    public String getFullName ()
    {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName (String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * @return the addrNation
     */
    public String getAddrNation ()
    {
        return addrNation;
    }

    /**
     * @param addrNation the addrNation to set
     */
    public void setAddrNation (String addrNation)
    {
        this.addrNation = addrNation;
    }

    /**
     * @return the addrState
     */
    public String getAddrState ()
    {
        return addrState;
    }

    /**
     * @param addrState the addrState to set
     */
    public void setAddrState (String addrState)
    {
        this.addrState = addrState;
    }

    /**
     * @return the addrCity
     */
    public String getAddrCity ()
    {
        return addrCity;
    }

    /**
     * @param addrCity the addrCity to set
     */
    public void setAddrCity (String addrCity)
    {
        this.addrCity = addrCity;
    }

    /**
     * @return the addrStreet
     */
    public String getAddrStreet ()
    {
        return addrStreet;
    }

    /**
     * @param addrStreet the addrStreet to set
     */
    public void setAddrStreet (String addrStreet)
    {
        this.addrStreet = addrStreet;
    }

    /**
     * @return the addrPhone
     */
    public String getAddrPhone ()
    {
        return addrPhone;
    }

    /**
     * @param addrPhone the addrPhone to set
     */
    public void setAddrPhone (String addrPhone)
    {
        this.addrPhone = addrPhone;
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
    
    /**
     * @return the requiredResponseMemo
     */
//    public String getRequiredResponseMemo ()
//    {
//        return requiredResponseMemo;
//    }
//
//    /**
//     * @param requiredResponseMemo the requiredResponseMemo to set
//     */
//    public void setRequiredResponseMemo (String requiredResponseMemo)
//    {
//        this.requiredResponseMemo = requiredResponseMemo;
//    }
//
//    /**
//     * @return the requiredResponseDueDate
//     */
//    public Date getRequiredResponseDueDate ()
//    {
//        return requiredResponseDueDate;
//    }
//    
//    /**
//     * @param requiredResponseDueDate the requiredResponseDueDate to set
//     */
//    public void setRequiredResponseDueDate (Date requiredResponseDueDate)
//    {
//        this.requiredResponseDueDate = requiredResponseDueDate;
//    }
    
    /**
     * @return the idBoard
     */
    public Long getIdBoard ()
    {
        return idBoard;
    }

    /**
     * @param idBoard the idBoard to set
     */
    public void setIdBoard (Long idBoard)
    {
        this.idBoard = idBoard;
    }
}
