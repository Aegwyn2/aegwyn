/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredentialDTO.java
 *
 * Created on Mar 31, 2017, 3:52:12 PM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.base.web.dto.StandardDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import aegwyn.core.cred.model.entity.Contact;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactDTO extends StandardDTO implements GeneralDTO<Contact>
{
    private Long systemId;
    private String firstName;
    private String lastName;
    private String email;
    private UserCredentialDTO cred;
    
    public ContactDTO()
    {

    }
    
    public ContactDTO(Contact _c)
    {
        setData(_c);
    }
    
//    public ContactDTO(Contact _c, boolean _setParent)
//    {
//        setData (_c, _setParent);
//    }
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
     * @return the firstName
     */
    public String getFirstName ()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName ()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail ()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail (String email)
    {
        this.email = email;
    }
    
    /**
     * @return the cred
     */
    public UserCredentialDTO getCred ()
    {
        return cred;
    }

    /**
     * @param cred the cred to set
     */
    public void setCred (UserCredentialDTO cred)
    {
        this.cred = cred;
    }

    @Override
    public void setData (Contact _c)
    {
        setTenantId (_c.getTenantId ());
        systemId = _c.getSystemId ();
        firstName = _c.getFirstName ();
        lastName = _c.getLastName ();
        email = _c.getEmail ();
        if(_c.getCredential () != null)
            cred = new UserCredentialDTO (_c.getCredential ());
    }
    
}
