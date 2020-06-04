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

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.cred.model.entity.Contact;
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
public class InviteDTO extends StandardDTO
{
    private List<String> emails;
    
    public InviteDTO()
    {
        
    }
  
    /**
     * @return the emails
     */
    public List<String> getEmails ()
    {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails (List<String> emails)
    {
        this.emails = emails;
    }
}
