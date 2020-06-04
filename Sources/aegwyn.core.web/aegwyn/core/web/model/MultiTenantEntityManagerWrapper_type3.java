/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * MultiTenantEntityManagerWrapper.java
 *
 * Created on Apr 11, 2017, 11:23:49 AM
 */

package aegwyn.core.web.model;

import javax.persistence.EntityManager;

/**
 *
 * @author Benny
 */
public interface MultiTenantEntityManagerWrapper_type3 extends EntityManager
{
    public void setEntityManagerLookup(String _s);
}
