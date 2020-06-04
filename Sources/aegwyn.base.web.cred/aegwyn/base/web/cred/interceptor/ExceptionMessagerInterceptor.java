/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ExceptionMessagerInterceptor.java
 *
 * Created on Jun 14, 2017, 2:32:34 PM
 */

package aegwyn.base.web.cred.interceptor;

import aegwyn.base.web.dto.StandardDTO;
import java.lang.reflect.Constructor;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Benny
 */
@ExceptionMessager
@Interceptor
public class ExceptionMessagerInterceptor 
{
    @AroundInvoke
    public Object exceptionMapping(InvocationContext _context) throws Exception { 
        try {
            System.out.println ("EXCEPTION MAPPING CALLED");
            Class<?> returnType = _context.getMethod ().getReturnType ();
            Constructor<?> constructor = returnType.getConstructor();
            StandardDTO retval = (StandardDTO) constructor.newInstance ();
            try {
                return _context.proceed ();
            }
            catch(Exception e) {
                e.printStackTrace ();
                retval.setErrorMessage ("ERROR, FAIL INTERNAL ERROR");
                return retval;
            }
        }
        catch(Exception e) {
            e.printStackTrace ();
            throw e;
        }
    }
}
