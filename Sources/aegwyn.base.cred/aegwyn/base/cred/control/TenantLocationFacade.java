/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantLocationFacade.java
 *
 * Created on Apr 19, 2017, 3:51:57 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.TenantLocation;
import aegwyn.core.web.util.Util;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Benny
 */
@Stateless
public class TenantLocationFacade 
{
    @PersistenceContext(unitName = "CustFeedbackLoginPU_schema_main")
    private EntityManager entityManager;
    
    public TenantLocation findTheLowestAmountTenantLocation()
    {
        TypedQuery<TenantLocation> query = entityManager.createNamedQuery ("TenantLocation.findTheLowestAmount", TenantLocation.class);
        
        try {
            return query.setMaxResults (1).getSingleResult ();
        }
        catch (NoResultException e) {
            return null;
        }
        catch (Exception ex) {
            Util.log (Level.SEVERE, "Error saat TenantLocation.findTheLowestAmount: " + ex.getMessage ());
            return null;
        }
    }
}
