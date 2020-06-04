/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * BoardFacade.java
 *
 * Created on Jun 15, 2017, 3:32:41 PM
 */

package aegwyn.base.feedback.control;

import aegwyn.core.cred.model.entity.Tenant;
import aegwyn.core.feedback.model.entity.Board;
import aegwyn.core.feedback.model.entity.Complaint;
import aegwyn.core.feedback.model.entity.Customer;
import aegwyn.core.feedback.model.entity.FAQ;
import aegwyn.core.feedback.model.entity.Feedback;
import aegwyn.core.web.qualifier.MultiTenant;
import java.util.List;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FeedbackFacade 
{
    @Inject @MultiTenant
    EntityManager entityManager;
    
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Feedback> T addFeedback(T _feedback) throws Exception {
        entityManager.persist (_feedback);
        return _feedback;
    }
    
    // not finished yet
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Feedback> T editFeedback(T _feedback, Class<T> _class) throws Exception {
        T oldObj = entityManager.find (_class, _feedback.getSystemId ());
        if(oldObj == null)
            return null;

        oldObj.setTenantId (_feedback.getTenantId ());
        
        Customer cust = _feedback.getCustomer ();
        Customer oldCust = null;
        if(cust != null) {
            if(cust.getSystemId () != null) {
                oldCust = entityManager.find(Customer.class, cust.getSystemId ());
            }
           
            if(oldCust != null) {
                oldCust.setFullName (cust.getFullName ());
                oldCust.setTenantId (cust.getTenantId ());
                oldCust.setAge (cust.getAge ());
                oldCust.setEmail (cust.getEmail ());
                oldCust.setGender (cust.getGender ());
                oldCust.setAddrCity (cust.getAddrCity ());
                oldCust.setAddrNation (cust.getAddrNation());
                oldCust.setAddrPhone (cust.getAddrPhone ());
                oldCust.setAddrState (cust.getAddrState ());
                oldCust.setAddrStreet (cust.getAddrStreet ());
                _feedback.setCustomer (oldCust);
            }
            else {
                cust.setSystemId (null);
                oldCust = oldObj.getCustomer ();
                if(oldCust != null) {
                    entityManager.remove (oldCust);
                    entityManager.flush ();
                }
            }
        }
        
        
        
        oldObj.setCustomer (_feedback.getCustomer ());
        oldObj.setCustReaction (_feedback.getCustReaction ());
        oldObj.setEmailList (_feedback.getEmailList ());
//        oldObj.setFeedbackCall (_feedback.isFeedbackCall ());
//        oldObj.setUrgentCall (_feedback.isUrgentCall ());
        oldObj.setFeedbackContent (_feedback.getFeedbackContent ());
        oldObj.setHasAction (_feedback.getHasAction ());
        oldObj.setHasImage (_feedback.getHasImage ());
        oldObj.setIssueDate (_feedback.getIssueDate ());
        oldObj.setMapLocation (_feedback.getMapLocation ());
        oldObj.setMemo (_feedback.getMemo ());
        oldObj.setPriority (_feedback.getPriority ());
//        oldObj.setRequiredResponse (_feedback.getRequiredResponse ());
        oldObj.setResponse (_feedback.getResponse ());
        oldObj.setSystemCreateDate (_feedback.getSystemCreateDate ());
        return oldObj;
       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Feedback> T deleteFeedback(int _id, Class<T> _class) throws Exception {
        T oldObj = entityManager.find (_class, _id);
        if(oldObj == null)
            return null;

        entityManager.remove (oldObj);

        return oldObj;
    }
    
    public <T extends Feedback> List<T> findFeedbackdByTenant(Tenant _t, Class<T> _class) throws Exception {
        
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(_class);
        Root<T> c = q.from(_class);
        ParameterExpression<String> p = cb.parameter(String.class);
        q.select(c).where(cb.equal (c.get("tenantId"), p));
        TypedQuery<T> query = entityManager.createQuery(q);
        query.setParameter(p, _t.getTenantId ());
        List<T> results = query.getResultList();
        if(results.size () <= 0)
            return null;
        
        return results;
    }
    
    public <T extends Feedback> List<T> findFeedbackByTenantId(String _tenantId, Class<T> _class) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(_class);
        Root<T> c = q.from(_class);
        ParameterExpression<String> p = cb.parameter(String.class);
        q.select(c).where(cb.equal (c.get("tenantId"), p));
        TypedQuery<T> query = entityManager.createQuery(q);
        query.setParameter(p, _tenantId);
        List<T> results = query.getResultList();
        if(results.size () <= 0)
            return null;
        
        return results;
        
    }   
}
