/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FAQFacade.java
 *
 * Created on May 30, 2017, 11:55:42 AM
 */

package aegwyn.base.feedback.control;

import aegwyn.core.cred.model.entity.Tenant;
import aegwyn.core.feedback.model.entity.Complaint;
import aegwyn.core.feedback.model.entity.ComplaintBoard;
import aegwyn.core.feedback.model.entity.FAQ;
import aegwyn.core.web.qualifier.MultiTenant;
import aegwyn.core.web.util.Util;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComplaintFacade 
{
    @Inject @MultiTenant
    EntityManager entityManager;
    
    @Resource
    SessionContext sc;
    
    public ComplaintBoard findComplaintById(Long _id) {
        ComplaintBoard board = entityManager.find (ComplaintBoard.class, _id);
        return board;
    }
}
