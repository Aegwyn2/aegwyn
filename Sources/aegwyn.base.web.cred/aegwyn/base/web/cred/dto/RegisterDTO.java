/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * RegisterDTO.java
 *
 * Created on May 18, 2017, 2:53:40 PM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.core.cred.model.entity.Contact;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterDTO extends ContactDTO
{
    private String companyName;
    
    public RegisterDTO()
    {
        
    }
    
    public RegisterDTO(Contact _con)
    {
        super (_con);
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
