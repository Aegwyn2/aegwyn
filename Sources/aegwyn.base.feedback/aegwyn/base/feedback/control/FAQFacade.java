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
public class FAQFacade 
{
    @Inject @MultiTenant
    EntityManager entityManager;
    
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FAQ addFAQ(String _tenantId, String _name, String _content) throws Exception {
        FAQ faq = new FAQ ();
        faq.setTenantId (_tenantId);
        faq.setName (_name);
        faq.setContent (_content);
        entityManager.persist (faq);
        return faq;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FAQ addFAQ(Tenant _tenant, String _name, String _content) throws Exception {
        FAQ faq = new FAQ ();
        faq.setTenant (_tenant);
        faq.setName (_name);
        faq.setContent (_content);
        entityManager.persist (faq);
        return faq;
       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FAQ editFAQ(long _id, String _name, String _content) throws Exception {
        FAQ oldFAQ = entityManager.find (FAQ.class, _id);
        if(oldFAQ == null)
            return null;

        oldFAQ.setName (_name);
        oldFAQ.setContent (_content);

        return oldFAQ;
       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FAQ deleteFAQ(long _id) throws Exception {
        FAQ oldFAQ = entityManager.find (FAQ.class, _id);
        if(oldFAQ == null)
            return null;

        entityManager.remove (oldFAQ);

        return oldFAQ;
    }
    
    public List<FAQ> findFAQByTenant(Tenant _t) throws Exception {
        TypedQuery<FAQ> query = entityManager.createNamedQuery ("FAQ.findByTenantId", FAQ.class)
                .setParameter ("tenantId", _t.getTenantId ());
        List<FAQ> listFAQ = query.getResultList ();
        if(listFAQ != null && listFAQ.size () <= 0)
            return null;
        
        return listFAQ;
    }
    
    public List<FAQ> findFAQByTenantId(String _tenantId) throws Exception {
        TypedQuery<FAQ> query = entityManager.createNamedQuery ("FAQ.findByTenantId", FAQ.class)  
                .setParameter ("tenantId", _tenantId);
        List<FAQ> listFAQ = query.getResultList ();
        if(listFAQ != null && listFAQ.size () <= 0)
            return null;
        
        return listFAQ;
    }
    
    public List<FAQ> findAllFAQ() throws Exception {
        TypedQuery<FAQ> query = entityManager.createNamedQuery ("FAQ.findAll", FAQ.class)  ;
        List<FAQ> listFAQ = query.getResultList ();
        if(listFAQ != null && listFAQ.size () <= 0)
            return null;
        
        return listFAQ;
    }
}
