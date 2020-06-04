/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * StandardMultiTenantEntityManager_type3.java
 *
 * Created on Apr 11, 2017, 11:54:24 AM
 */

package aegwyn.core.web.model;

import aegwyn.core.web.qualifier.MultiTenant;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author Benny
 */
@RequestScoped
@MultiTenant
public class MultiTenantEntityManager implements EntityManager
{
    @Resource
    SessionContext context;
    
//    @EJB
    @Inject
    WebApplicationContext wapc;
    
    private EntityManager getEntityManager()
    {
        EntityManager manager = (EntityManager) context.lookup(wapc.getWebRequestInfo ().getPersistenceJNDILocation ());
      
        if (manager == null) {
            throw new RuntimeException("Tenant unknown");
        }
        return manager;
    }
    
    @Override
    public void persist (Object _entity)
    {
        getEntityManager ().persist (_entity);
    }

    @Override
    public <T> T merge (T _entity)
    {
        return getEntityManager ().merge (_entity);
    }

    @Override
    public void remove (Object _entity)
    {
        getEntityManager ().remove (_entity);
    }

    @Override
    public <T> T find (Class<T> _entityClass, Object _primaryKey)
    {
        return getEntityManager ().find (_entityClass, _primaryKey);
    }

    @Override
    public <T> T find (Class<T> _entityClass, Object _primaryKey, Map<String, Object> _properties)
    {
        return getEntityManager ().find (_entityClass, _primaryKey, _properties);
    }

    @Override
    public <T> T find (Class<T> _entityClass, Object _primaryKey, LockModeType _lockMode)
    {
        return getEntityManager ().find (_entityClass, _primaryKey, _lockMode);
    }

    @Override
    public <T> T find (Class<T> _entityClass, Object _primaryKey, LockModeType _lockMode, Map<String, Object> _properties)
    {
        return getEntityManager ().find (_entityClass, _primaryKey, _lockMode, _properties);
    }

    @Override
    public <T> T getReference (Class<T> _entityClass, Object _primaryKey)
    {
        return getEntityManager ().getReference (_entityClass, _primaryKey);
    }

    @Override
    public void flush ()
    {
        getEntityManager ().flush ();
    }

    @Override
    public void setFlushMode (FlushModeType _flushMode)
    {
        getEntityManager ().setFlushMode (_flushMode);
    }

    @Override
    public FlushModeType getFlushMode ()
    {
        return getEntityManager ().getFlushMode ();
    }

    @Override
    public void lock (Object _entity, LockModeType _lockMode)
    {
        getEntityManager ().lock (_entity, _lockMode);
    }

    @Override
    public void lock (Object _entity, LockModeType _lockMode, Map<String, Object> _properties)
    {
        getEntityManager ().lock (_entity, _lockMode, _properties);
    }

    @Override
    public void refresh (Object _entity)
    {
        getEntityManager ().refresh (_entity);
    }

    @Override
    public void refresh (Object _entity, Map<String, Object> _properties)
    {
        getEntityManager ().refresh (_entity, _properties);
    }

    @Override
    public void refresh (Object _entity, LockModeType _lockMode)
    {
        getEntityManager ().refresh (_entity, _lockMode);
    }

    @Override
    public void refresh (Object _entity, LockModeType _lockMode, Map<String, Object> _properties)
    {
        getEntityManager ().refresh (_entity, _lockMode, _properties);
    }

    @Override
    public void clear ()
    {
        getEntityManager ().clear ();
    }

    @Override
    public void detach (Object _entity)
    {
        getEntityManager ().detach (_entity);
    }

    @Override
    public boolean contains (Object _entity)
    {
        return getEntityManager ().contains (_entity);
    }

    @Override
    public LockModeType getLockMode (Object _entity)
    {
        return getEntityManager ().getLockMode (_entity);
    }

    @Override
    public void setProperty (String _propertyName, Object _value)
    {
        getEntityManager ().setProperty (_propertyName, _value);
    }

    @Override
    public Map<String, Object> getProperties ()
    {
        return getEntityManager ().getProperties ();
    }

    @Override
    public Query createQuery (String _qlString)
    {
        return getEntityManager ().createQuery (_qlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery (CriteriaQuery<T> _criteriaQuery)
    {
        return getEntityManager ().createQuery (_criteriaQuery);
    }

    @Override
    public Query createQuery (CriteriaUpdate _updateQuery)
    {
        return getEntityManager ().createQuery (_updateQuery);
    }

    @Override
    public Query createQuery (CriteriaDelete _deleteQuery)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> TypedQuery<T> createQuery (String _qlString, Class<T> _resultClass)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Query createNamedQuery (String _name)
    {
        return getEntityManager ().createNamedQuery (_name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery (String _name, Class<T> _resultClass)
    {
        return getEntityManager ().createNamedQuery (_name, _resultClass);
    }

    @Override
    public Query createNativeQuery (String _sqlString)
    {
        return getEntityManager ().createNativeQuery (_sqlString);
    }

    @Override
    public Query createNativeQuery (String _sqlString, Class _resultClass)
    {
        return getEntityManager ().createNativeQuery (_sqlString, _resultClass);
    }

    @Override
    public Query createNativeQuery (String _sqlString, String _resultSetMapping)
    {
        return getEntityManager ().createNativeQuery (_sqlString, _resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery (String _name)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery (String _procedureName)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery (String _procedureName, Class... _resultClasses)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery (String _procedureName, String... _resultSetMappings)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void joinTransaction ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isJoinedToTransaction ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T unwrap (Class<T> _cls)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getDelegate ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOpen ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityTransaction getTransaction ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder ()
    {
        return getEntityManager ().getCriteriaBuilder ();
    }

    @Override
    public Metamodel getMetamodel ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph (Class<T> _rootType)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityGraph<?> createEntityGraph (String _graphName)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityGraph<?> getEntityGraph (String _graphName)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs (Class<T> _entityClass)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
