/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantLocation.java
 *
 * Created on Apr 19, 2017, 3:43:28 PM
 */

package aegwyn.core.cred.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="tenantlocation")
@NamedQueries ({
    @NamedQuery (name="TenantLocation.findTheLowestAmount", query="SELECT T FROM TenantLocation T WHERE T.amount = "
            + "(SELECT MIN(TL.amount) FROM TenantLocation TL)"),
})
public class TenantLocation 
{
    @Id
    private Integer no;
    
    private Integer amount;
    
    /**
     * @return the no
     */
    public void setNo (Integer _no)
    {
        no = _no;
    }

    /**
     * @return the no
     */
    public Integer getNo ()
    {
        return no;
    }
    
    /**
     * @return the amount
     */
    public void setAmount (Integer _amount)
    {
        amount = _amount;
    }

    /**
     * @return the amount
     */
    public Integer getAmount ()
    {
        return amount;
    }   
}
