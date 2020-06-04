/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantDTO.java
 *
 * Created on May 22, 2017, 9:52:37 AM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.cred.entity.Tenant;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenantDTO extends StandardDTO implements GeneralDTO<Tenant>
{
    private String companyName;
    
    public TenantDTO()
    {
        
    }
    
    public TenantDTO(Tenant _tenant) 
    {
        setData (_tenant);
    }
    
    @Override
    public void setData (Tenant _data)
    {
        setTenantId (_data.getTenantId ());
        companyName = _data.getCompanyName ();
    }

    /**
     * @return the companyName
     */
    public String getCompanyName ()
    {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }

}
