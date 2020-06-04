/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredFacade.java
 *
 * Created on Apr 25, 2017, 1:51:48 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.Contact;
import aegwyn.core.cred.entity.UserCredential;

/**
 *
 * @author Benny
 */
public interface UserCredFacade {
    public Contact validate (String _userName, String _password) throws Exception;
    public UserCredential findById (Long _id) throws Exception;
}
