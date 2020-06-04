/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserDTO.java
 *
 * Created on May 24, 2017, 3:57:49 PM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.cred.entity.Tenant;
import aegwyn.core.cred.entity.UserCredential;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO extends StandardDTO
{
    
    private Long systemId;
    private String firstName;
    private String lastName;
    private String userName;
    private String userGroup;
    private String pwd;
    private String email;
    
//    public void setData(Tenant _tenant, Contact _con) throws Exception {
//        boolean conEqualTenantId = _con.getTenantId ().equals (_tenant.getTenantId ());
//        
//        if(!conEqualTenantId)
//            throw new Exception("ERROR IN SETTING DTO DATA, DIFFERENT TENANT ID");
//        
//        setData (_tenant);
//        setData (_con);        
//    }
    
    public void  setData(RegisteredUser _user) {
        email = _user.getEmail ();
        setData (_user.getTenant ());
    }
    
    public void setData(Contact _con) {
        setTenantId (_con.getTenantId ());
        systemId = _con.getSystemId ();
        firstName = _con.getFirstName ();
        lastName = _con.getLastName ();
        email = _con.getEmail ();
        
        if(_con.getCredential () != null) {
            setData (_con.getCredential ());
        }
    }
    
    public void setData(UserCredential _cred) {
        setTenantId (_cred.getTenantId ());
        userName = _cred.getUserName ();
        if(_cred.getGroup () != null)
            userGroup = _cred.getGroup ().getName ();
        pwd = _cred.getPwd ();
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
     * @return the userName
     */
    public String getUserName ()
    {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    /**
     * @return the userGroup
     */
    public String getUserGroup ()
    {
        return userGroup;
    }

    /**
     * @param userGroup the userGroup to set
     */
    public void setUserGroup (String userGroup)
    {
        this.userGroup = userGroup;
    }

    /**
     * @return the pwd
     */
    public String getPwd ()
    {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd (String pwd)
    {
        this.pwd = pwd;
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
}
