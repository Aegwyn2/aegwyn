/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * LoginRequiredInterceptor.java
 *
 * Created on Apr 21, 2017, 2:00:16 PM
 */

package aegwyn.base.web.cred.interceptor;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.base.web.cred.model.ApplicationContext;
import aegwyn.core.cred.model.entity.RegisteredUser;
import aegwyn.core.web.model.UserSession;
import aegwyn.core.web.model.UserSessionContainer;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Benny
 */
@LoginRequired
@Interceptor
public class LoginRequiredInterceptor 
{

    @Inject
    ApplicationContext apc;
    
    @Inject
    UserSessionContainer usc;
    
    @AroundInvoke
    public Object loginRequired(InvocationContext _context) throws Exception {
            System.out.println ("LOGIN REQUIRED CALLED");
        Object[] parameters = _context.getParameters ();
        Class<?> returnType = _context.getMethod ().getReturnType ();
        Constructor<?> constructor = returnType.getConstructor();
        StandardDTO retval = (StandardDTO) constructor.newInstance ();
        StandardDTO dto = null;
        String sessionString = null;
                
        for (Object parameter : parameters) {
            if(parameter instanceof StandardDTO) {
                dto = (StandardDTO) parameter;
                sessionString = dto.getSessionString ();
                System.out.println ("SS: " + sessionString);
                break;
            }
            
            if(parameter instanceof String) {
                sessionString = (String) parameter;
            }
        }
        
        if(sessionString == null || sessionString.length () <= 0) {
            throw new Exception("ERROR, NO USER DTO WITH SESSION STRING OR STRING ARGUMENTS");
        }
        else {
                    System.out.println ("SESSION STRING: " + sessionString);

            UserSession us = usc.getSession (sessionString, false);
            RegisteredUser user = null;
            if(us != null && us.getSessionName ().equals ("Login")) {
                user = (RegisteredUser) us.getObject ("user");
                if(user != null) {
                    apc.setCurrentUser (user);
                    us.setLastActivity (Calendar.getInstance ());
                }
                else {
                    throw new Exception("ERROR, CAN't FIND THE USER INFO IN THE CURRENT LOGIn SESSION");
                }
            }
            else {
                retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
            }
        }
                
        if(retval.getErrorMessage () == null) 
            return _context.proceed ();
        else 
            return retval;
    }
}
