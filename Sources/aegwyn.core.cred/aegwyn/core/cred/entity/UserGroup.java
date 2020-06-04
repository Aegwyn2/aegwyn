/**
 * UserGroup.java
 *
 * Created on March 24, 2014
 */
package aegwyn.core.cred.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.UuidGenerator;
//import sunwell.xrp.util.ui.ISimpleObjectStringAdapter;

/**
 * @author Yoga
 * @author Irfin
 * @version 1.0
 * @created 24-Mar-2014 13:34:03
 */
@Entity
@Table (name = "usergroup")
@NamedQueries ({
    @NamedQuery(name="UserGroup.findBySystemId", query="SELECT u FROM UserGroup u WHERE u.systemId = :sysid"),
    @NamedQuery(name="UserGroup.findAll", query="SELECT u FROM UserGroup u ORDER BY u.name"),
    @NamedQuery(name="UserGroup.findByName", query="SELECT u FROM UserGroup u WHERE UPPER(u.name) = UPPER(:name) ")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserGroup implements Serializable,
                                  Comparable<UserGroup>
{
    @Id
    @SequenceGenerator (name = "usergroup_systemid_seq", sequenceName = "usergroup_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "usergroup_systemid_seq" )
    @Column (name = "systemid", insertable = false)
    private Integer systemId;
    
    private String tenantId;
    
    @Column (name = "name")
    private String name;
    
    @Column (name = "memo")
    private String memo;
    
    @XmlTransient
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    @JoinColumn (name = "usergrp")
    private List<AccessRights> accessRights;
    
    @XmlTransient
    @ManyToOne
    @JoinColumn (name = "sys_creator")
    private UserCredential sysCreator;
    
    @Column (name = "sys_createdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar sysCreateDate;
    
    @XmlTransient
    @ManyToOne
    @JoinColumn (name = "sys_lastupdater")
    private UserCredential sysLastUpdater;
    
    @Column (name = "sys_lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar sysLastUpdate;

    public UserGroup ()
    {
    }
    
    /**
     * @return the tenantId
     */
    public String getTenantId ()
    {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    /**
     * @return the m_systemid
     */
    public Integer getSystemId ()
    {
        return systemId;
    }

    /**
     * @param m_systemid the m_systemid to set
     */
    public void setSystemId (Integer m_systemid)
    {
        this.systemId = m_systemid;
    }

    /**
     * @return the m_name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param m_name the m_name to set
     */
    public void setName (String m_name)
    {
        this.name = m_name;
    }

    /**
     * @return the m_memo
     */
    public String getMemo ()
    {
        return memo;
    }

    /**
     * @param m_memo the m_memo to set
     */
    public void setMemo (String m_memo)
    {
        this.memo = m_memo;
    }
    
    public void setAccessRights (List<AccessRights> _listAR)
    {
        accessRights = _listAR;
        if (accessRights != null)
            for (AccessRights ar : accessRights) {
                ar.setOwner (this);
            }
    }
    
    public List<AccessRights> getAccessRights ()
    {
        return accessRights;
    }
    
    /**
     * Mengembalikan nilai access-bit untuk jenis aktivitas yang dilewatkan ke
     * parameter {@code _taskType}. Jika tidak ditemukan jenis aktivitas yang dicari,
     * maka method ini menganggap bahwa hak-akses memang tidak diberikan, dan 
     * mengembalikan {@link AccessRights#BIT_NOACCESS} (nilai 0).
     * 
     * @param _taskType
     * @return 
     */
    public int getAccessBitForTask (int _taskType)
    {
        if (accessRights == null)
            return AccessRights.BIT_NOACCESS;
        
        for (AccessRights ar : accessRights) {
            if (ar.getTaskType () == _taskType)
                return ar.getAccessBit ();
        }
        
        return AccessRights.BIT_NOACCESS;
    }
    
    /**
     * Memeriksa apakah objek UserGroup ini diberikan akses untuk melakukan aksi
     * {@code _actionBit} pada aktivitas {@code _taskType}. Jika ya, nilai true
     * dikembalikan, sebaliknya false.
     * 
     * @param _taskType
     * @param _actionBit
     * @return 
     */
    public boolean canPerform (int _taskType, int _actionBit)
    {
        if (accessRights == null)
            return false;
        
        for (AccessRights ar : accessRights) {
            if (ar.getTaskType () == _taskType) {
                if ((ar.getAccessBit () & _actionBit) > AccessRights.BIT_NOACCESS)
                    return true;
            }
        }
        
        return false;
    }

    /**
     * @return the m_sys_creator
     */
    public UserCredential getSysCreator ()
    {
        return sysCreator;
    }

    /**
     * @param m_sys_creator the m_sys_creator to set
     */
    public void setSysCreator (UserCredential m_sys_creator)
    {
        this.sysCreator = m_sys_creator;
    }

    /**
     * @return the m_sys_createdate
     */
    public Calendar getSysCreateDate ()
    {
        return sysCreateDate;
    }

    /**
     * @param m_sys_createdate the m_sys_createdate to set
     */
    public void setSysCreateDate (Calendar m_sys_createdate)
    {
        this.sysCreateDate = m_sys_createdate;
    }

    /**
     * @return the m_sys_lastupdater
     */
    public UserCredential getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    /**
     * @param m_sys_lastupdater the m_sys_lastupdater to set
     */
    public void setSysLastUpdater (UserCredential m_sys_lastupdater)
    {
        this.sysLastUpdater = m_sys_lastupdater;
    }

    /**
     * @return the m_sys_lastupdate
     */
    public Calendar getSysLastUpdate ()
    {
        return sysLastUpdate;
    }

    /**
     * @param m_sys_lastupdate the m_sys_lastupdate to set
     */
    public void setSysLastUpdate (Calendar m_sys_lastupdate)
    {
        this.sysLastUpdate = m_sys_lastupdate;
    }

    @Override
    public int hashCode ()
    {
        return systemId.hashCode ();
    }

    @Override
    public int compareTo (UserGroup _o)
    {
        return this.name.compareTo (_o.name);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (!(obj instanceof UserGroup)) {
            return false;
        }
        
        UserGroup other = (UserGroup) obj;
        if ((this.systemId == null && other.systemId != null) || 
            (this.systemId != null && !this.systemId.equals(other.systemId))) {
            return false;
        }
        return false;
    }
 
    @Override
    public String toString ()
    {
        return name;
    }
}
