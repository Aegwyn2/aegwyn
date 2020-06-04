/**
 * UserCredFacade.java
 *
 * Created on Mar 20, 2017, 3:56:07 PM
 */
package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.UserCredential;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import aegwyn.core.cred.entity.Contact;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import aegwyn.core.web.model.CounterTest;
import aegwyn.core.web.qualifier.MultiTenant;
import aegwyn.core.web.util.Util;
import javax.ejb.EJB;
import aegwyn.core.web.model.MultiTenantEntityManagerWrapper;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StandardUserCredFacade implements UserCredFacade
{

    
    @Inject @MultiTenant
    EntityManager entityManager;
        
    @Resource
    SessionContext sc;
    
    /**
     * 
     * @param _userName
     * @param _password
     * @return 
     */
    public Contact validate (String _userName, String _password) throws Exception
    {
//        if(_userName == null || _userName.length () <= 0 || _password == null || _password.length () <= 0 )
//            return null;
            
        TypedQuery<UserCredential> query = entityManager.createNamedQuery ("UserCredential.findByUsername", UserCredential.class);
        query.setParameter ("uname", _userName);
        
        try {
            Object o = query.getSingleResult ();            
            UserCredential uc = query.getSingleResult ();

            if (uc != null) {
                // Hitung MD5 dari password masukan user.
                String passDariUser = Util.generateMD5 (_password);

                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ hasil MD5 oleh java = " + passDariUser);
                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ passwd di DB = " + uc.getPwd ());

                // Periksa password
                if (passDariUser.equals (uc.getPwd ())) {
                    // Query data Contact-nya.
                    TypedQuery<Contact> queryContact = entityManager.createNamedQuery ("Contact.findByCredId", Contact.class);
                    queryContact.setParameter ("credid", uc.getSystemId ());
                    return queryContact.getSingleResult ();
                }
                else
                    return null;
            }
            else
                return null;
        }
        catch (NoResultException ex) {
            ex.printStackTrace ();
            return null;
        }
    }
    
    public UserCredential findById (Long _id)
    {
//        if(_id == null)
//            return null;
        
        UserCredential uc = entityManager.find (UserCredential.class, _id);
        return uc;
    }
}
