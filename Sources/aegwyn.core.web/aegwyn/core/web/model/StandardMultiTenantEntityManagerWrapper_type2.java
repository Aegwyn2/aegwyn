/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * MultiTenantEntityManager.java
 *
 * Created on Apr 11, 2017, 10:39:33 AM
 */

package aegwyn.core.web.model;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Benny
 */
@Stateless
public class StandardMultiTenantEntityManagerWrapper_type2 implements MultiTenantEntityManagerWrapper_type2 
{
    @Resource
    SessionContext context;
    
    private EntityManager getEntityManager(String _emLookup)
    {
        EntityManager manager = (EntityManager) context.lookup(_emLookup);
      
        if (manager == null) {
            throw new RuntimeException("Tenant unknown");
        }
        return manager;
    }

    @Override
    public void persist (Object _entity, String _emLookup)
    {
        getEntityManager (_emLookup).persist (_entity);
    }

    @Override
    public void merge (Object _entity, String _emLookup)
    {
        getEntityManager (_emLookup).merge (_entity);
    }

    @Override
    public void remove (Object _entity, String _emLookup)
    {
        getEntityManager (_emLookup).remove (_entity);
    }

    @Override
    public <T> TypedQuery<T> createnamedQuery (String _query, Class<T> _result, String _emLookup)
    {
        return getEntityManager (_emLookup).createNamedQuery (_query, _result);
    }
    
}
