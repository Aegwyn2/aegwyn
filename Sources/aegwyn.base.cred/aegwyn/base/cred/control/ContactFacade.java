/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ContactFacade.java
 *
 * Created on Apr 25, 2017, 1:53:43 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.Tenant;
import java.util.List;

/**
 *
 * @author Benny
 */
public interface ContactFacade {
    public List<Contact> findByEmail (String _s) throws Exception;
    public Contact findByEmailAndTenant (String _s, Tenant _t) throws Exception;
    public Contact findByEmailAndTenantId (String _s, String _tenantId) throws Exception;
}
