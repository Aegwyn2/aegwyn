/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ComplaintBoard.java
 *
 * Created on Jun 15, 2017, 2:01:12 PM
 */

package aegwyn.core.feedback.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="suggestionboard")
@NamedQueries({
    @NamedQuery(name = "SuggestionBoard.findAll", query = "SELECT B FROM SuggestionBoard B"),
    @NamedQuery(name = "SuggestionBoard.findByTenantId", query = "SELECT B FROM SuggestionBoard B WHERE B.tenantId = :tenantId")
})
public class SuggestionBoard extends Board
{
//    @Id
//    @SequenceGenerator (name = "complaint_systemid_seq", sequenceName = "complaint_systemid_seq", allocationSize = 1)
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "complaint_systemid_seq" )
//    @Column(name = "systemid")
//    private int systemId;
//    
//    private String tenantId;
//    
//    private String name;
//    
//    private String content;
//    
//    /**
//     * @return the systemId
//     */
//    public int getSystemId ()
//    {
//        return systemId;
//    }
//
//    /**
//     * @param systemId the systemId to set
//     */
//    public void setSystemId (int systemId)
//    {
//        this.systemId = systemId;
//    }
//    
//    /**
//     * @return the tenantId
//     */
//    public String getTenantId ()
//    {
//        return tenantId;
//    }
//
//    /**
//     * @param tenantId the tenantId to set
//     */
//    public void setTenantId (String tenantId)
//    {
//        this.tenantId = tenantId;
//    }
//
//    /**
//     * @return the name
//     */
//    public String getName ()
//    {
//        return name;
//    }
//
//    /**
//     * @param name the name to set
//     */
//    public void setName (String name)
//    {
//        this.name = name;
//    }
//
//    /**
//     * @return the content
//     */
//    public String getContent ()
//    {
//        return content;
//    }
//
//    /**
//     * @param content the content to set
//     */
//    public void setContent (String content)
//    {
//        this.content = content;
//    }
}
