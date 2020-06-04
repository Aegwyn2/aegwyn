/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ApplicationConfig.java
 *
 * Created on Mar 30, 2017, 11:37:46 AM
 */

package aegwyn.service.login;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Benny
 */
@javax.ws.rs.ApplicationPath("resource") 
public class ApplicationConfig extends Application 
{

    @Override
    public Set<Class<?>> getClasses ()
    {
        Set<Class<?>> resources = new java.util.HashSet<> ();
        addRestResourceClasses (resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses (Set<Class<?>> resources)
    {
        resources.add (aegwyn.service.login.ContactFacadeREST.class);
        resources.add (aegwyn.service.login.CrossOriginResourceSharingFilter.class);
        resources.add (aegwyn.service.login.CustomExceptionMapper.class);
        resources.add (aegwyn.service.login.LoginWebService.class);
        resources.add (aegwyn.service.login.UserCredentialFacadeREST.class);
        resources.add (aegwyn.service.login.UserGroupFacadeREST.class);
    }

}
