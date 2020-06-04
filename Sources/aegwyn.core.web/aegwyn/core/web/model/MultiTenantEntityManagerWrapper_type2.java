/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * MultiTenantEntityManager.java
 *
 * Created on Apr 11, 2017, 10:40:46 AM
 */

package aegwyn.core.web.model;

import javax.persistence.TypedQuery;

/**
 *
 * @author Benny
 */
public interface MultiTenantEntityManagerWrapper_type2 {
  
    public void persist(Object entity, String _emLookup);
    public void merge(Object _entity, String _emLookup);
    public void remove(Object entity, String _emLookup);
    public <T> TypedQuery<T> createnamedQuery(String _query, Class<T> _result, String _emLookup);
}
