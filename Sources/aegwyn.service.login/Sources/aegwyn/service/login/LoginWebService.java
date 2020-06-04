/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ContactFacadeREST.java
 *
 * Created on Mar 30, 2017, 11:37:50 AM
 */

package aegwyn.service.login;

import aegwyn.base.cred.control.ContactFacade;
import aegwyn.base.cred.control.TenantFacade;
import aegwyn.base.cred.control.UserCredFacade;
import aegwyn.core.cred.entity.Contact;
import aegwyn.base.web.cred.dto.ContactDTO;
import aegwyn.base.web.cred.dto.UserCredentialDTO;
import aegwyn.core.cred.entity.Tenant;
import aegwyn.base.cred.control.RegistrationFacade;
import aegwyn.base.web.cred.dto.InviteDTO;
import aegwyn.base.web.cred.dto.ListTenantDTO;
import aegwyn.base.web.cred.dto.RegisterDTO;
import aegwyn.base.web.dto.StandardDTO;
import aegwyn.base.web.cred.dto.TenantDTO;
import aegwyn.base.web.cred.dto.UserDTO;
import aegwyn.base.web.cred.interceptor.ExceptionMessager;
import aegwyn.core.web.model.UserSession;
import aegwyn.base.web.cred.interceptor.LoginRequired;
import aegwyn.global.web.interceptor.NewTenant;
import aegwyn.global.web.interceptor.NewUser;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import aegwyn.global.web.interceptor.TenantRequired;
import aegwyn.base.web.cred.model.ApplicationContext;
import aegwyn.core.cred.entity.RegisteredUser;
import aegwyn.core.web.model.UserSessionContainer;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Benny
 */
@Stateless 
@Path("")
public class LoginWebService 
{
    @EJB
    UserCredFacade userCredFacade;
    
    @EJB
    ContactFacade contactFacade;
    
    @EJB
    TenantFacade tenantFacade;
    
    @EJB
    RegistrationFacade registrationfacade;
    
    @Inject 
    ApplicationContext apc;  
    
    @Inject
    UserSessionContainer usc;
    
    @Resource
    SessionContext sc;
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionMessager
    @TenantRequired
    public UserDTO login(UserDTO _dto) throws Exception
    {
        UserDTO retval = new UserDTO();
        if(_dto.getEmail() == null || _dto.getEmail ().length () <= 0)
            retval.setErrorMessage("ERROR, NO EMAIL IN THE DTO");
        else {
            Tenant currentTenant = apc.getCurrentTenant ();
            if(currentTenant == null)
                throw new Exception("ERROR, THERE IS NO TENANT FOR CURRENT REQUEST");
            Contact contact = contactFacade.findByEmailAndTenant (_dto.getEmail (), currentTenant);
            if(contact == null)
                retval.setErrorMessage ("ERROR, CAN't FIND THE CONTACT FOR THE SPECIFIED MAIL AND TENANT");
            else if(contact.getCredential () == null)
                retval.setErrorMessage("ERROR, CAN't FIND THE CREDENTIAL FOR THIS CONTACT");
            else {
                Contact credContact = userCredFacade.validate (contact.getCredential ().getUserName (), _dto.getPwd ());
                if(credContact == null) {
                    retval.setErrorMessage ("ERROR, CAN'T FIND THE REGISTERED CONTACT");
                }
                else {
                    retval.setData (credContact);
                    UserSession us = usc.newSession ();
                    us.setSessionName ("Login");
                    us.addObject ("user", apc.getCurrentUser ());
                    us.addObject ("tenant", apc.getCurrentTenant ());
                    us.setLastActivity (Calendar.getInstance ());
                    retval.setSessionString (us.getSessionId ());
                }
            }
        }
        return retval;
    }
    
    @POST                                   
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    @ExceptionMessager
    @LoginRequired
    public StandardDTO logout(StandardDTO _dto) throws Exception {
        System.out.println ("LOG OUt CALLED");
        StandardDTO retval = new StandardDTO();
        boolean result = usc.removeSession (_dto.getSessionString ());
        if(!result)
            retval.setErrorMessage ("ERROR, CAN'T FIND THE SPECIFIED SESSION");
        
        return retval;
    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    @NewTenant
    public UserDTO register(@Context HttpServletRequest _req, UserDTO _dto) throws Exception
    {
        UserDTO retval = new UserDTO ();
        if(_dto.getFirstName () == null || _dto.getFirstName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO FIRST NAME IS SPECIFIED");
        else if(_dto.getLastName () == null || _dto.getLastName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO LAST NAME IS SPECIFIED");
        else if(_dto.getUserName () == null || _dto.getUserName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO USER NAME IS SPECIFIED");
        else if(_dto.getEmail () == null || _dto.getEmail ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO EMAIL IS SPECIFIED");
        else if(_dto.getPwd () == null || _dto.getPwd ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO PASSWORD IS SPECIFIED");
        else {
            RegisteredUser user = apc.getCurrentUser ();
            if(user == null)
                throw new Exception("ERROR, NO REGISTERED USER FOR CURRENT REQUEST");
            Tenant tenant = user.getTenant ();
            if(tenant == null)
                throw new Exception("ERROR, NO TENANT FOR CURRENT REQUEST");
            Contact exstCon = contactFacade.findByEmailAndTenantId (_dto.getEmail (), tenant.getTenantId ());
            if(exstCon != null)
                retval.setErrorMessage ("ERROR, EMAIL ALREADY EXIST");
            else {
                Contact con = registrationfacade.register (tenant.getTenantId (), _dto.getFirstName (), _dto.getLastName (), _dto.getUserName (),
                        _dto.getEmail (), _dto.getPwd ());
                if(con == null) {
                    throw new Exception("ERROR, CAN'T REGISTER NEW USER");
                }
                else {
                    retval.setData (con);
                    retval.setLocationNo (tenant.getTenantLocation ().getNo ());
                    UserSession us = usc.getSession (null, true);
                    us.setSessionName ("Login");
                    us.addObject ("user", user);
                    us.addObject ("tenant", tenant);
                    retval.setSessionString (us.getSessionId ());
                }
            }
        }
        return retval;
    }
    
    @GET
    @Path("/tenantinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionMessager
    public TenantDTO getTenantInfo(@QueryParam("companyName") String _companyName) throws Exception {
        TenantDTO retval = new TenantDTO();
        if(_companyName == null || _companyName.length () <= 0)
            retval.setErrorMessage("ERROR, NO COMPANY NAME IS SPECIFIED");
        else {
            Tenant tenant = tenantFacade.findByCompanyName (_companyName);
            if(tenant == null) 
                retval.setErrorMessage ("ERROR CAN't FIND TENANT");
            else 
                retval.setData (tenant);
        }
        return retval;
    }
 
    @POST                                   
    @Path("/inviteusers")
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    @LoginRequired
    public StandardDTO inviteUsers(InviteDTO _dto) throws Exception {
        StandardDTO retval = new StandardDTO();
        if(_dto.getEmails () == null || _dto.getEmails ().size () <= 0)
            retval.setErrorMessage ("ERROR, NO EMAILS SPECIFIED");
        else {
            Tenant currentTenant = apc.getCurrentTenant ();
            if(currentTenant == null)
                throw new Exception("ERROR, NO TENANT FOR CURRENT REQUEST");
            for (String email : _dto.getEmails ()) {
                if(email == null || email.length () <= 0) {
                    retval.setErrorMessage ("THE SPECIFIED EMAILS LIST IS NOT VALID ");
                    sc.setRollbackOnly ();
                    break;
                }
                tenantFacade.registerUser (currentTenant, email, false);
            }
        }
        return retval;
    }
    
    @GET                                   
    @Path("/joingrouplist")
    @Produces(MediaType.APPLICATION_JSON)   
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    public ListTenantDTO joinGroupList(@QueryParam("email") String _email) throws Exception {
        ListTenantDTO retval = new ListTenantDTO ();;
        if(_email == null || _email.length () <= 0) 
            retval.setErrorMessage ("ERROR< NO EMAIL IS SPECIFIED");
        else {
            List<RegisteredUser> users = tenantFacade.findUserByEmail (_email);
            List<Tenant> listTenant = new LinkedList<>();
            if(users != null && users.size () > 0) {
                for (RegisteredUser user : users) {
                    if(!user.isCreated ()) {
                        Tenant t = user.getTenant ();
                        if(t == null) {
                            throw new Exception("ERROR, NO TENANT FOR THE USER: " + user.getEmail ());
                        }
                        listTenant.add (user.getTenant ());
                    }
                }
                retval.setData (listTenant);
            }
        }
        return retval;
    }
    
    @POST                                   
    @Path("/createprofile")
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    @TenantRequired
    public UserDTO createProfile(@Context HttpServletRequest _req, UserDTO _dto) throws Exception {
        UserDTO retval = new UserDTO();
        if(_dto.getFirstName () == null || _dto.getFirstName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO FIRST NAME IS SPECIFIED");
        else if(_dto.getLastName () == null || _dto.getLastName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO LAST NAME IS SPECIFIED");
        else if(_dto.getUserName () == null || _dto.getUserName ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO USER NAME IS SPECIFIED");
        else if(_dto.getEmail () == null || _dto.getEmail ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO EMAIL IS SPECIFIED");
        else if(_dto.getPwd () == null || _dto.getPwd ().length () <= 0 )
            retval.setErrorMessage ("ERROR, NO PASSWORD IS SPECIFIED");
        else {
            RegisteredUser user = apc.getCurrentUser ();
            if(user == null)
                throw new Exception("ERROR, NO REGISTERED USER FOR THIS REQUEST");
            Tenant tenant = apc.getCurrentTenant ();
            if(tenant == null)
                throw new Exception("ERROR, NO TENANT FOR THIS REQUEST");
            registrationfacade.setRequest (_req);
            user.setCreated (true);
            Contact exstCon = contactFacade.findByEmailAndTenantId (_dto.getEmail (), tenant.getTenantId ());
            if(exstCon != null)
                retval.setErrorMessage ("ERROR, EMAIL ALREADY EXIST");
            else {
                Contact con = registrationfacade.register (tenant.getTenantId (), _dto.getFirstName (), _dto.getLastName (), _dto.getUserName (), 
                        _dto.getEmail (), _dto.getPwd ());
                if(con == null) 
                    throw new Exception("ERROR, CAN'T CREATE PROFILE FOR THIS USER");
                else {
                    retval.setData (con);
                    retval.setLocationNo (tenant.getTenantLocation ().getNo ());
                }
            }
        }
        return retval;
    }
    
    @GET                                   
    @Path("/sessioninfo")
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionMessager
    @LoginRequired
    public UserDTO getSessionInfo(@QueryParam("sessionString") String _session) {
        UserDTO retval = new UserDTO();
        if(_session == null || _session.length () <= 0)
            retval.setErrorMessage ("ERROR, NO SESSSION STRING SPECIFIED");
        else {
            UserSession session = usc.getSession (_session, false);
            if(session == null)
                retval.setErrorMessage ("ERROR, NO SESSSION IS ACTIVE");
            else {
                RegisteredUser user = apc.getCurrentUser ();
                retval.setData (user);
            }
        }
        return retval;
    }

}
