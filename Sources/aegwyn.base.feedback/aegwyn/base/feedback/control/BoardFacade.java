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
import aegwyn.core.feedback.model.entity.FAQ;
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
public class BoardFacade 
{
    @Inject @MultiTenant
    EntityManager entityManager;
    
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Board> T addBoard(Tenant _tenant, String _name, String _content, Class<T> _class) throws Exception {
        T obj = _class.newInstance ();
        obj.setTenantId (_tenant.getTenantId ());
        obj.setName (_name);
        obj.setContent (_content);
        entityManager.persist (obj);
        return obj;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Board> T addBoard(String _tenantId, String _name, String _content, Class<T> _class) throws Exception {
        T obj = _class.newInstance ();
        obj.setTenantId (_tenantId);
        obj.setName (_name);
        obj.setContent (_content);
        entityManager.persist (obj);
        return obj;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Board> T editBoard(long _id, String _name, String _content, Class<T> _class) throws Exception {
        T oldObj = entityManager.find (_class, _id);
        if(oldObj == null)
            return null;

        oldObj.setName (_name);
        oldObj.setContent (_content);

        return oldObj;
       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends Board> T deleteBoard(long _id, Class<T> _class) throws Exception {
        T oldObj = entityManager.find (_class, _id);
        if(oldObj == null)
            return null;

        entityManager.remove (oldObj);

        return oldObj;
    }
    
    public <T extends Board> List<T> findBoardByTenant(Tenant _t, Class<T> _class) throws Exception {
        
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
    
    public <T extends Board> List<T> findBoardByTenantId(String _tenantId, Class<T> _class) throws Exception {
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
        
    public <T extends Board> T findBoardId(Long _id, Class<T> _class) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(_class);
        Root<T> c = q.from(_class);
        ParameterExpression<Long> p = cb.parameter(Long.class);
        q.select(c).where(cb.equal (c.get("systemId"), p));
        TypedQuery<T> query = entityManager.createQuery(q);
        query.setParameter(p, _id);
        T result = query.getSingleResult ();
        
        return result;
    }
}
