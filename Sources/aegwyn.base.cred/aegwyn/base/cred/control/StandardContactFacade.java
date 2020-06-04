/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ContactFacade.java
 *
 * Created on Mar 27, 2017, 3:09:15 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.Tenant;
import aegwyn.core.web.qualifier.MultiTenant;
import aegwyn.core.web.util.Util;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.inject.Inject;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StandardContactFacade implements ContactFacade
{
    @Inject @MultiTenant
    EntityManager entityManager;
    
    public List<Contact> findByEmail (String _s) throws Exception
    {
        if(_s == null || _s.length () <= 0)
            return null;
        
        TypedQuery<Contact> query = entityManager.createNamedQuery ("Contact.findByEmail", Contact.class);
        query.setParameter ("email", _s);
        List<Contact> result = query.getResultList ();
        if(result.size () <= 0)
            return null;

        return result;       
    }
    
    public Contact findByEmailAndTenant (String _email, Tenant _t) throws Exception
    {
//        if(_email == null || _email.length () <= 0 || _t == null)
//            return null;
        
        try {
            TypedQuery<Contact> query = entityManager.createNamedQuery ("Contact.findByEmailAndTenantId", Contact.class);
            query.setParameter ("email", _email)
                 .setParameter ("tenantId", _t.getTenantId ());
            return query.getSingleResult ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
    
    public Contact findByEmailAndTenantId (String _email, String _id) throws Exception
    {
//        if(_email == null || _email.length () <= 0 || _id == null || _id.length () <= 0)
//            return null;
        try {
            TypedQuery<Contact> query = entityManager.createNamedQuery ("Contact.findByEmailAndTenantId", Contact.class);
            query.setParameter ("email", _email)
                 .setParameter ("tenantId", _id);
            return query.getSingleResult ();
        }
        catch(NoResultException e) {
            return null;
        }
       
    }
}
