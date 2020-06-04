/**
 * SignUpCtrl.java
 *
 * Created on Mar 22, 2017, 2:53:26 PM
 */

package aegwyn.base.cred.control;

//import control.ServletCtx;
import aegwyn.base.cred.control.ContactFacade;
import aegwyn.base.cred.control.StandardUserCredFacade;
import aegwyn.core.cred.entity.UserCredential;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import aegwyn.core.cred.entity.Contact;
import aegwyn.base.cred.control.StandardContactFacade;
import aegwyn.base.cred.control.UserCredFacade;
import aegwyn.core.cred.entity.SignUpHistory;
import aegwyn.core.web.util.UserAgentInfo;
import aegwyn.core.web.util.Util;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import aegwyn.core.web.model.MultiTenantEntityManagerWrapper;

/**
 *
 * @author Benny
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RegistrationFacade implements Serializable
{
//    public static final int SUCCESS = 0;
//    public static final int FAIL_EMAIL_EXISTS = 1;
//    public static final int FAIL_PASSWORD_INVALID = 2;
//    public static final int FAIL_EMAIL_FORMAT_INVALID = 3;
//    public static final int FAIL_INTERNAL_ERROR = 4;
    
    private HttpServletRequest httpReq;
    private UserAgentInfo uai;
        
    @EJB
    MultiTenantEntityManagerWrapper entityManager;
    
    @EJB
    private ContactFacade contactFacade;
    
    @EJB
    private UserCredFacade credFacade;
    
    @EJB
    private TenantFacade tenantFacade;
    
    private int countReqCall = 0;
    
    @Resource
    SessionContext sc;
    

    public RegistrationFacade ()
    {
    }
    
    public void setRequest (HttpServletRequest _httpReq) throws Exception
    {
        httpReq = _httpReq;
        uai = Util.extractUserAgentInfo (httpReq);
    }
 
    /**
     * 
     * @param _firstname
     * @param _lastname
     * @param _email
     * @param _password
     * @return 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Contact register (String _tenantId, String _firstname, String _lastname, 
                             String _userName, String _email, String _password) throws Exception
    {
        // TODO: Hapus jika tdk dipakai lagi
//        countReqCall++;
//        Util.log (Level.INFO, "Nilai var countReqCall: " + countReqCall);
        
        
        // Urutan proses:
        // 1. Periksa apakah email sdh digunakan. Jika sudah, maka exit.
        // 2. Ciptakan objek UserCredential.
        // 3. Ciptakan objek Contact dan pasangkan objek UserCredential dari #1.
        // 4. Ciptakan objek SignUpHistory.
        // 5. Commit.
        
        
//        if(_tenantId == null || _tenantId.length () <= 0 ||
//           _firstname == null || _firstname.length () <= 0 ||
//           _lastname == null || _lastname.length () <= 0 ||
//           _userName == null || _userName.length () <= 0 ||
//           _email == null || _email.length () <= 0 ||
//           _password == null || _password.length () <= 0)
//            return null;
        
        // #1
//        if (isEmailAlreadyRegistered (_email, _tenantId))
//            return null;        

            // #2
            UserCredential uc = new UserCredential ();
            uc.setTenantId (_tenantId);
            uc.setSysCreateDate (Calendar.getInstance ());
            uc.setUserName (_userName);
            uc.setPwd (_password);

            entityManager.persist (uc);
            
            // #3
            Contact con = new Contact ();
            con.setTenantId (_tenantId);
            con.setFirstName (_firstname);
            con.setLastName (_lastname);
            con.setEmail (_email);
            con.setCredentials (uc);

            entityManager.persist (con);

            // #4
            SignUpHistory sh = new SignUpHistory ();
            sh.setTenantId (_tenantId);
            sh.setEmail (_email);
            sh.setRegisterTime (Calendar.getInstance ());
            
            if(uai != null) {
                sh.setClientUI (uai.userAgent);
                sh.setRegisterFromIP (uai.ipAddress);
            }

            entityManager.persist (sh);

            // #5
//            entityManager.getTransaction ().commit ();

            return con;
    }
    
    private boolean isEmailAlreadyRegistered (String _email, String _tenantId) throws Exception
    {
        return contactFacade.findByEmailAndTenantId (_email, _tenantId) != null;
    }
    
    public List<SignUpHistory> findByEmail (String _email) throws Exception
    {
        if(_email == null || _email.length () <= 0)
            return null;
        
        Query query = entityManager.createNamedQuery ("SignUpHistory.findByEmail");
        query.setParameter ("email", _email);
        List<SignUpHistory> result = query.getResultList ();
        if (result.size () <= 0)
            return null;

        return result;
    }
    
}
