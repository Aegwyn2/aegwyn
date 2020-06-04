/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * MultiTenantRequestInterceptor.java
 *
 * Created on Apr 26, 2017, 9:51:31 AM
 */

package aegwyn.global.web.interceptor;

import static aegwyn.base.cred.control.StandardTenantFacade.EMAIL_ALREADY_EXIST;
import static aegwyn.base.cred.control.StandardTenantFacade.FAIL_INTERNAL_ERROR;
import static aegwyn.base.cred.control.StandardTenantFacade.TENANT_ALREADY_EXIST;
import aegwyn.base.cred.control.TenantFacade;
import aegwyn.base.web.cred.model.ApplicationContext;
import aegwyn.base.web.cred.dto.ContactDTO;
import aegwyn.base.web.cred.dto.RegisterDTO;
import aegwyn.base.web.dto.StandardDTO;
import aegwyn.base.web.cred.dto.UserCredentialDTO;
import aegwyn.base.web.cred.dto.UserDTO;
import aegwyn.core.cred.model.entity.RegisteredUser;
import aegwyn.core.cred.model.entity.Tenant;
import aegwyn.core.web.model.WebApplicationContext;
import java.lang.reflect.Constructor;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Benny
 */
@NewTenant
@Interceptor
public class NewTenantInterceptor 
{
    
    @EJB
    TenantFacade tenantFacade;

    @Inject
    ApplicationContext apc;
    
    @Resource
    SessionContext sc;
    
    @AroundInvoke
    public Object newTenant(InvocationContext _context) throws Exception
    {
        Object[] parameters = _context.getParameters ();
        Class<?> returnType = _context.getMethod ().getReturnType ();
        Constructor<?> constructor = returnType.getConstructor();
        StandardDTO retval = (StandardDTO) constructor.newInstance ();
        UserDTO dto = null;
        
        for (Object parameter : parameters) {
           if(parameter instanceof UserDTO) {
               dto = (UserDTO) parameter;
           }
        }
         
        if(dto == null)
            throw new Exception("ERROR NO USER DTO ON THE ARGUMENT LIST");
        else if(dto.getCompanyName () == null || dto.getCompanyName ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO COMPANY NAME");
        else {
            if(tenantFacade.findByCompanyName (dto.getCompanyName ()) != null) 
                retval.setErrorMessage ("ERROR, TENANt ALREADY EXISTS");
            else {
                Tenant tenant = tenantFacade.registerTenant (dto.getEmail (), dto.getCompanyName ());
                if(tenant == null) {
                    throw new Exception("ERROR, CAN'T REGISTER TENANT");
//                    sc.setRollbackOnly ();
                }
                else {
                    RegisteredUser user = tenantFacade.registerUser (tenant, dto.getEmail (), true);
                    if(user == null) {
                        throw new Exception("ERROR, CAN'T REGISTER USER");
//                        sc.setRollbackOnly ();
                    }
                    else {
                        apc.setCurrentUser (user);
                    }
                }
            }
        }
        
        if(retval.getErrorMessage () == null)
            return _context.proceed ();
        else
            return retval;
    }
}
