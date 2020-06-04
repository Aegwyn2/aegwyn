/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Board.java
 *
 * Created on Jun 15, 2017, 2:34:23 PM
 */

package aegwyn.core.feedback.model.entity;

import aegwyn.core.cred.entity.Tenant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="board")
@Inheritance (strategy = InheritanceType.JOINED)
public class Board 
{
    private String tenantId;
    
    @Transient
    private Tenant tenant;

    @Id
    @SequenceGenerator (name = "board_systemid_seq", sequenceName = "board_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "board_systemid_seq" )
    @Column(name = "systemid")
    private Long systemId;
    
    @Column(name="boardname")
    private String name;
    
    @Column(name="boardcontent")
    private String content;
    
    public Board() {
        
    }
    
    /**
     * @return the tenant
     */
    public Tenant getTenant ()
    {
        return tenant;
    }
    
    /**
     * @param tenant the tenant to set
     */
    public void setTenant (Tenant tenant)
    {
        this.tenant = tenant;
        this.tenantId = tenant.getTenantId ();
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
     * @return the systemId
     */
    public Long getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (Long systemId)
    {
        this.systemId = systemId;
    }

    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * @return the content
     */
    public String getContent ()
    {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent (String content)
    {
        this.content = content;
    }
}
