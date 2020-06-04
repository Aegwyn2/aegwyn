/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ComplaintDTO.java
 *
 * Created on Jul 5, 2017, 11:09:17 AM
 */

package aegwyn.base.web.feedback.dto;

import aegwyn.core.feedback.model.entity.Complaint;
import aegwyn.core.feedback.model.entity.Feedback;
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
public class ComplaintDTO extends FeedbackDTO
{

    private Boolean feedbackCall;
    
    private Boolean urgentCall;
    
    private String requiredResponseMemo;
        
    @XmlJavaTypeAdapter(DateTimeAdapter.class)    
    private Date requiredResponseDueDate;

    public ComplaintDTO() {
        
    }
    
    public void setData(Complaint _complaint) {
        setFeedbackCall (_complaint.isFeedbackCall ());
        setUrgentCall (_complaint.isUrgentCall ());
        setData((Feedback)_complaint);
    }
    
    /**
     * @return the feedbackCall
     */
    public Boolean getFeedbackCall ()
    {
        return feedbackCall;
    }

    /**
     * @param feedbackCall the feedbackCall to set
     */
    public void setFeedbackCall (Boolean feedbackCall)
    {
        this.feedbackCall = feedbackCall;
    }

    /**
     * @return the urgentCall
     */
    public Boolean getUrgentCall ()
    {
        return urgentCall;
    }

    /**
     * @param urgentCall the urgentCall to set
     */
    public void setUrgentCall (Boolean urgentCall)
    {
        this.urgentCall = urgentCall;
    }
    
    /**
     * @return the requiredResponseMemo
     */
    public String getRequiredResponseMemo ()
    {
        return requiredResponseMemo;
    }

    /**
     * @param requiredResponseMemo the requiredResponseMemo to set
     */
    public void setRequiredResponseMemo (String requiredResponseMemo)
    {
        this.requiredResponseMemo = requiredResponseMemo;
    }

    /**
     * @return the requiredResponseDueDate
     */
    public Date getRequiredResponseDueDate ()
    {
        return requiredResponseDueDate;
    }

    /**
     * @param requiredResponseDueDate the requiredResponseDueDate to set
     */
    public void setRequiredResponseDueDate (Date requiredResponseDueDate)
    {
        this.requiredResponseDueDate = requiredResponseDueDate;
    }
}
