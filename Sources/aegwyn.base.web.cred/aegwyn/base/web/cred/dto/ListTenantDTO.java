/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ListTenantDTO.java
 *
 * Created on Jun 12, 2017, 3:16:36 PM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.cred.entity.Tenant;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListTenantDTO extends StandardDTO
{
    private List<TenantDTO> listTenant = new LinkedList<>();

    /**
     * @return the listTenant
     */
    public List<TenantDTO> getListTenant ()
    {
        return listTenant;
    }

    /**
     * @param listTenant the listTenant to set
     */
    public void setListTenant (List<TenantDTO> listTenant)
    {
        this.listTenant = listTenant;
    }
    
    public void setData(List<Tenant> _listTenant) {
        for (Tenant tenant : _listTenant) {
            TenantDTO dto = new TenantDTO ();
            dto.setData (tenant);
            listTenant.add (dto);
        }
    }
}
