/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aegwyn.service.login.servlet;

import aegwyn.base.cred.control.RegistrationFacade;
import aegwyn.core.web.model.WebApplicationContext;
import aegwyn.core.web.util.Util;
import java.io.IOException;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sunwell
 */
public class SignUpServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    RegistrationFacade signUpCtrl;
    
//    @EJB
    @Inject
    WebApplicationContext apc;
    
//    protected void processRequest (HttpServletRequest _request, HttpServletResponse _response)
//            throws ServletException, IOException
//    {
//        String firstname = _request.getParameter ("firstname");
//        String lastname = _request.getParameter ("lastname");
//        String email = _request.getParameter ("email");
//        String password = _request.getParameter ("password");
//        apc.getTenantInfo ().setJndiLocation ("java:comp/env/persistence/LOCATION0");
//        
//        _response.setContentType ("text/html;charset=UTF-8");
//        
////        SignUpCtrl ctrl = new SignUpCtrl (request);
////        int ret = ctrl.register (firstname, lastname, email, password);
//
////        Util.log (Level.INFO, "Masuk Servlet");
//        
//        signUpCtrl.setRequest (_request);
////        Util.log (Level.INFO, "Memanggil method register");
//        int ret = signUpCtrl.register (firstname, lastname, email, password);
//        
//        Util.log (Level.INFO, "RET IN SIGNUP: " + ret);
//        if (ret == SignUpCtrl.SUCCESS) {
//            _response.getWriter ().println ("Success menyimpan user baru");
//        }
//        else if (ret == SignUpCtrl.FAIL_EMAIL_EXISTS) {
//            // panggil kelas outputer yg bisa menangani kejadian ini.
//            _response.getWriter ().println ("Fail email sudah ada");
//        }
//        else if (ret == SignUpCtrl.FAIL_INTERNAL_ERROR) {
//            // panggil kelas outputer yg sesuai.
//            _response.getWriter ().println ("Fail internal error");
//        }
////        Util.log (Level.INFO, "Clean up di servlet");
////        signUpCtrl.doCleanUp ();
//        
//        
////        try (PrintWriter out = response.getWriter ()) {
////            /* TODO output your page here. You may use following sample code. */
////            out.println ("<!DOCTYPE html>");
////            out.println ("<html>");
////            out.println ("<head>");
////            out.println ("<title>Servlet SignUpServlet</title>");            
////            out.println ("</head>");
////            out.println ("<body>");
////            out.println ("<h1>Servlet SignUpServlet at " + request.getContextPath () + "</h1>");
////            out.println ("</body>");
////            out.println ("</html>");
////        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest (request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest (request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

}
