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

import aegwyn.base.cred.control.TenantFacade;
import aegwyn.base.web.cred.dto.ContactDTO;
import aegwyn.base.web.cred.model.ApplicationContext;
import aegwyn.base.web.dto.StandardDTO;
import aegwyn.base.web.cred.dto.UserCredentialDTO;
import aegwyn.base.web.cred.dto.UserDTO;
import aegwyn.core.cred.model.entity.RegisteredUser;
import aegwyn.core.cred.model.entity.Tenant;
import java.lang.reflect.Constructor;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Benny
 */
@TenantRequired
@Interceptor
public class TenantRequiredInterceptor 
{
    
    @EJB
    TenantFacade tenantFacade;
    
    @Inject
    ApplicationContext apc;
    
    @AroundInvoke
    public Object tenantRequired(InvocationContext _context) throws Exception
    {
        Object[] parameters = _context.getParameters ();
        Class<?> returnType = _context.getMethod ().getReturnType ();
        Constructor<?> constructor = returnType.getConstructor();
        StandardDTO retval = (StandardDTO) constructor.newInstance ();
        UserDTO dto = null;
        RegisteredUser user = null;
                
        for (Object parameter : parameters) {
            if(parameter instanceof UserDTO) {
                dto = (UserDTO) parameter;
            }
        }
        
        if(dto == null)
            throw new Exception("NO USER DTO ON THE ARGUMENT LIST");
        else if(dto.getTenantId () == null || dto.getTenantId ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO TENANT ID");
        else {
            Tenant tenant = tenantFacade.findByTenantId (dto.getTenantId ());

            if(tenant == null)
                retval.setErrorMessage ("ERROR, CAN'T FIND THE TENANT WITH THE SPECIFIED EMAIL");

            user = tenantFacade.findUserByEmailAndTenant (dto.getEmail (), tenant);

            if(user == null)
                retval.setErrorMessage ("ERROR, CAN'T FIND THE USER WITH THE SPECIFIED EMAIL");
            else {
                apc.setCurrentUser (user);
            }
        }
                
        if(retval.getErrorMessage () == null)
            return _context.proceed ();
        else
            return retval;
    }
}
