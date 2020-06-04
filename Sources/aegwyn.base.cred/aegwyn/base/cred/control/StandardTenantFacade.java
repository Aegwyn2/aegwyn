/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantFacade.java
 *
 * Created on Apr 19, 2017, 3:03:47 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.cred.entity.Tenant;
import aegwyn.core.cred.entity.TenantLocation;
import aegwyn.core.web.model.WebApplicationContext;
import aegwyn.core.web.util.Util;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StandardTenantFacade implements TenantFacade
{
//    public static int SUCCESS = 0;
//    public static int TENANT_ALREADY_EXIST = 1;
//    public static int EMAIL_ALREADY_EXIST = 2;
//    public static int FAIL_INTERNAL_ERROR = 3;

    @PersistenceContext(unitName = "CustFeedbackLoginPU_schema_main")
    private EntityManager entityManager;
    
    @EJB
    RegistrationFacade ctrl;
    
    @EJB
    TenantLocationFacade tlFacade;
    
    @Inject
    WebApplicationContext apc;
    
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Tenant registerTenant(String _email, String _companyName) throws Exception
    {
//        if(_email == null || _email.length () <= 0 || _companyName == null || _companyName.length () <= 0)
//            return null;
        
        Tenant t = new Tenant();
        TenantLocation tl = tlFacade.findTheLowestAmountTenantLocation ();
        
        if(tl == null)
            throw new Exception ("Tidak menemukan lokasi untuk tenant");
        
        t.setTenantLocation (tl);
        t.setEmail (_email);
        t.setCompanyName (_companyName);
        
        entityManager.persist (t);
        tl.setAmount (tl.getAmount () + 1); 
        
        return t;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public RegisteredUser registerUser(Tenant _t, String _email, boolean _created) throws Exception
    {
//        if(_t == null || _email == null || _email.length () <= 0 )
//            return null;
        
        RegisteredUser usr = new RegisteredUser ();
        usr.setTenant (_t);
        usr.setEmail (_email);
        usr.setCreated (_created);
        
        entityManager.persist (usr);
        return usr;
    }
    
    @Override
    public Tenant findByTenantId (String _id) throws Exception
    {
//        if(_id == null || _id.length () <= 0)
//            return null;
        try {
            TypedQuery<Tenant> query = entityManager.createNamedQuery ("Tenant.findByTenantId", Tenant.class);
            query.setParameter ("id", _id);
            return query.getSingleResult ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
    
    @Override
    public List<Tenant> findByEmail (String _email) throws Exception
    {
//        if(_email == null || _email.length () <= 0 )
//            return null;
        try {
            TypedQuery<Tenant> query = entityManager.createNamedQuery ("Tenant.findByEmail", Tenant.class);
            query.setParameter ("email", _email);
            return query.getResultList ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Tenant findByCompanyName(String _name) throws Exception
    {
//        if(_name == null || _name.length () <= 0)
//            return null;
        
        try {
            TypedQuery<Tenant> query = entityManager.createNamedQuery ("Tenant.findByCompanyName", Tenant.class);
            query.setParameter ("name", _name);
            return query.getSingleResult ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
    
    @Override
    public List<RegisteredUser> findUserByEmail (String _email) throws Exception
    {
//        if(_email == null || _email.length () <= 0)
//            return null;
            
        TypedQuery<RegisteredUser> query = entityManager.createNamedQuery ("RegisteredUser.findByEmail", RegisteredUser.class);
        query.setParameter ("email", _email);
        List<RegisteredUser> resultList = query.getResultList ();
        if(resultList.size () > 0)
            return query.getResultList ();
        else
            return null;
    }
    
    @Override
    public List<RegisteredUser> findUserByTenant (Tenant _tenant) throws Exception
    {
//        if(_tenant == null)
//            return null;
        
        try {
            TypedQuery<RegisteredUser> query = entityManager.createNamedQuery ("RegisteredUser.findByTenant", RegisteredUser.class);
            query.setParameter ("tenant", _tenant);
            return query.getResultList ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
    
    @Override
    public RegisteredUser findUserByEmailAndTenant (String _email, Tenant _tenant) throws Exception
    {
//        if(_email == null || _email.length () <= 0 || _tenant == null)
//            return null;
        try {
            TypedQuery<RegisteredUser> query = entityManager.createNamedQuery ("RegisteredUser.findByEmailAndTenant", RegisteredUser.class);
            query.setParameter ("email", _email)
                 .setParameter ("tenant", _tenant);
            return query.getSingleResult ();
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
