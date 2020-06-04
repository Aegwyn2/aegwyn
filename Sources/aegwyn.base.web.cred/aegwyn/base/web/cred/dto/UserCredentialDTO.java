/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredentialDTO.java
 *
 * Created on Mar 31, 2017, 4:38:08 PM
 */

package aegwyn.base.web.cred.dto;

import aegwyn.base.web.dto.StandardDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import aegwyn.core.cred.model.entity.UserCredential;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCredentialDTO extends StandardDTO implements GeneralDTO<UserCredential>
{
    private String userName;
    private String pwd;
    private String userGroup;
    
    public UserCredentialDTO()
    {

    }
    
    public UserCredentialDTO(UserCredential _uc)
    {
        setData(_uc);
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

    @Override
    public void setData (UserCredential _uc)
    {
        setTenantId (_uc.getTenantId ());
        userName = _uc.getUserName ();
        pwd = _uc.getPwd ();
        userGroup = _uc.getGroup () != null ? _uc.getGroup ().getName () : null;
    }
}
