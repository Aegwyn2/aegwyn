/*
 * AccessRights.java
 *
 * Created on Feb 5, 2015, 4:54:52 PM
 */
package aegwyn.core.cred.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author irfin
 */
@IdClass (AccessRightsPK.class)
@Entity
@Table (name = "usergroupaccessrights")
@Inheritance (strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "AccessRights.findAll", query = "SELECT a FROM AccessRights a"),
    @NamedQuery(name = "AccessRights.findByUserGroup", query = "SELECT a FROM AccessRights a WHERE a.owner = :usergrp")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessRights implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    public static final int BIT_NOACCESS = 0;
    public static final int BIT_CREATE   = 1;
    public static final int BIT_READ     = 2;
    public static final int BIT_UPDATE   = 4;
    public static final int BIT_DELETE   = 8;
    public static final int BIT_VIEW_MONEY_AMOUNT = 16;
    protected static final int SUM_ACCESS_BIT = BIT_CREATE | BIT_READ | BIT_UPDATE | BIT_DELETE | BIT_VIEW_MONEY_AMOUNT;
    
    protected static final int MIN_TASK_ID = 0;
    protected static final int MAX_TASK_ID = 19;
    public static final int TASK_PURCHASE_COMMITEMENT = MIN_TASK_ID;
    public static final int TASK_PURCHASE_ORDER = 1;
    public static final int TASK_REGISTER_ITEM = 2;
    public static final int TASK_VENDOR_INVOICE = 3;
    public static final int TASK_VENDOR_PAYMENT = 4;
    public static final int TASK_VENDOR_RETURN = 5;
    public static final int TASK_SALES_QUOTE = 6;
    public static final int TASK_SALES_ORDER = 7;
    public static final int TASK_SALES_INVOICE = 8;
    public static final int TASK_SALES_PAYMENT = 9;
    public static final int TASK_SALES_RETURN = 10;
    public static final int TASK_ONHAND_STOCK = 11;
    public static final int TASK_STOCK_ADJUSTMENT = 12;
    public static final int TASK_STOCK_TRANSFER = 13;
    public static final int TASK_STOCK_RESERVATION = 14;
    public static final int TASK_DELIVERY_ORDER = 15;
    public static final int TASK_DELIVERY_ORDER_CONFIRMATION = 16;
    public static final int TASK_SETUP_ITEM = 17;
    public static final int TASK_SETUP_SERVICE = 18;
    public static final int TASK_ACCESS_RIGHTS = MAX_TASK_ID;
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usergrp", referencedColumnName = "systemid")
    private UserGroup owner;
    
    @Id
    @Column(name = "tasktype")
    private Integer taskType;
    
    @Column(name = "accessbit")
    private Integer accessBit;
    
    private String tenantId; 

    public AccessRights ()
    {
    }

    public UserGroup getOwner ()
    {
        return owner;
    }

    public void setOwner (UserGroup usergrp)
    {
        this.owner = usergrp;
    }
    
    protected void setTaskType (Integer _taskType)
    {
        taskType = _taskType;
    }
    
    public Integer getTaskType ()
    {
        return taskType;
    }
    
    protected void setAccessBit (Integer _accBit)
    {
        accessBit = _accBit;
    }
    
    /**
     * Mengembalikan nilai integer yg mewakili hak akses. Untuk mengetahui apakah
     * suatu hak akses dimiliki atau tidak, caranya adalah lakukan operasi
     * BIT-AND pada nilai kembalian fungsi ini dengan nilai konstanta BIT_XXX yang
     * dideklarasikan di kelas ini. Kontanta BIT_XXX dipilih sesuai hak akses yang
     * ingin diketahui. Jika hasil operasi BIT-AND menghasilkan nilai > 0, berarti
     * hak akses tersebut ada (TRUE).
     * 
     * @return 
     */
    public Integer getAccessBit ()
    {
        return accessBit;
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
     * Menentukan jenis task/aktivitas dan hak aksesnya. Gunakan konstanta public
     * yg disediakan oleh kelas ini untuk mengeset nilai kedua parameter.
     * 
     * @param _taskType
     * @param _accBit
     * @throws RuntimeException jika _taskType atau _accBit tidak sesuai dengan
     *  nilai salah satu konstanta yg dimiliki kelas ini.
     */
    public void setTaskTypeAndAccessBit (Integer _taskType, Integer _accBit) throws RuntimeException
    {
//        if (_taskType < MIN_TASK_ID || _taskType > MAX_TASK_ID)
//            throw new RuntimeException ("Nilai TaskType tidak valid");
//        else if (_accBit < 0 || _accBit > SUM_ACCESS_BIT)
//            throw new RuntimeException ("Nilai AccessBit tidak valid");
        
        taskType = _taskType;
        accessBit = _accBit;
    }

    @Override
    public int hashCode ()
    {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode (this.owner);
        hash = 79 * hash + Objects.hashCode (this.taskType);
        return hash;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;
        if (getClass () != obj.getClass ())
            return false;
        
        final AccessRights that = (AccessRights) obj;
        if (!Objects.equals (this.owner, that.owner))
            return false;
        if (!Objects.equals (this.taskType, that.taskType))
            return false;
        
        return true;
    }

   
    
    /**
     * Mengembalikan daftar AccessRights. CACHE YG ADA AKAN DIKOSONGKAN TERLEBIH DAHULU.
     * @return 
     */
//    public static List<AccessRights> getAll ()
//    {
//        EntityManager em = MainAppRuntime.getEntityManager ();
//        em.getEntityManagerFactory ().getCache ().evict (AccessRights.class);
//        
//        TypedQuery jpaQuery = em.createNamedQuery ("AccessRights.findAll", AccessRights.class);
//        return jpaQuery.getResultList ();
//    }
//    
//    /**
//     * 
//     * @param _ug terdefinisi (bukan null).
//     */
//    public static void deleteAllForUserGroup (UserGroup _ug)
//    {
//        EntityManager em = MainAppRuntime.getEntityManager ();
//        List<AccessRights> listRights;
//        TypedQuery jpaQuery;
//        
//        jpaQuery = em.createNamedQuery ("AccessRights.findByUserGroup", AccessRights.class);
//        jpaQuery.setParameter ("usergrp", _ug);
//
//        listRights = jpaQuery.getResultList ();
//        if (listRights == null) {
//            System.out.println ("listRights NULL for deletion purpose.");
//            return;
//        }
//
//        for (AccessRights rights : listRights) {
//            em.remove (rights);
//        }
//    }
}
