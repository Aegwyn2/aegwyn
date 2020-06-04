/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Question.java
 *
 * Created on May 26, 2017, 11:15:48 AM
 */

package aegwyn.core.feedback.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="complaint")
public class Complaint extends Feedback
{
    
    @ManyToOne
    @JoinColumn(name="idboard")
    private ComplaintBoard complaintBoard;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "complaint")
    private RequiredResponse requiredResponse;
    
    @Column(name="feedbackcall")
    private Boolean feedbackCall;
    
    @Column(name="urgentcall")
    private Boolean urgentCall;
    
    /**
     * @return the complainBoard
     */
    public ComplaintBoard getComplaintBoard ()
    {
        return complaintBoard;
    }

    /**
     * @param complainBoard the complainBoard to set
     */
    public void setComplaintBoard (ComplaintBoard complainBoard)
    {
        this.complaintBoard = complainBoard;
    }
    
    /**
     * @return the feedbackCall
     */
    public Boolean isFeedbackCall ()
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
    public Boolean isUrgentCall ()
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
     * @return the requiredResponse
     */
    public RequiredResponse getRequiredResponse ()
    {
        return requiredResponse;
    }

    /**
     * @param requiredResponse the requiredResponse to set
     */
    public void setRequiredResponse (RequiredResponse requiredResponse)
    {
        this.requiredResponse = requiredResponse;
    }
    
}
