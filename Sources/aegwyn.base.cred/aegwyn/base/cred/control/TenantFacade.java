/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantFacade.java
 *
 * Created on Apr 25, 2017, 1:49:23 PM
 */

package aegwyn.base.cred.control;

import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.cred.entity.Tenant;
import java.util.List;

/**
 *
 * @author Benny
 */
public interface TenantFacade {
    
    public static int SUCCESS = 0;
    public static int TENANT_ALREADY_EXIST = 1;
    public static int EMAIL_ALREADY_EXIST = 3;
    public static int FAIL_INTERNAL_ERROR = 4;
    
    
//    public int register(String _firstname, String _lastName, String _pwd, String _email) throws Exception;
    public Tenant registerTenant(String _email, String _companyName) throws Exception;
    public RegisteredUser registerUser(Tenant _t, String _email, boolean _created) throws Exception;
    public Tenant findByTenantId (String _id) throws Exception;
    public List<Tenant> findByEmail (String _email) throws Exception;
    public Tenant findByCompanyName(String _companyName) throws Exception;
    public List<RegisteredUser> findUserByEmail (String _email) throws Exception;
    public RegisteredUser findUserByEmailAndTenant (String _email, Tenant _tenant) throws Exception;
    public List<RegisteredUser> findUserByTenant (Tenant _tenant) throws Exception;
}
