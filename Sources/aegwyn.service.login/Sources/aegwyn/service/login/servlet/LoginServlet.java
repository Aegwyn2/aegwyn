package aegwyn.service.login.servlet;

import aegwyn.base.cred.control.StandardUserCredFacade;
//import aegwyn.core.cred.ContactXMLWrapper;
import aegwyn.core.cred.entity.Contact;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.JsonWriter;

/**
 *
 * @author sunwell
 */
public class LoginServlet extends HttpServlet
{
    @EJB
    StandardUserCredFacade userCredFacade;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            String acrHeaders = request.getHeader("Access-Control-Request-Headers");
            String acrMethod = request.getHeader("Access-Control-Request-Method");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", acrHeaders);
            response.setHeader("Access-Control-Allow-Methods", acrMethod);
            response.setContentType ("text/html;charset=UTF-8");

            String name = request.getParameter ("name");
            String password = request.getParameter ("password");
            String jsonstring = request.getParameter ("jsonString");

            System.out.println ("Name: " + name + " password: " + password);
            System.out.println ("JSOn String: " + jsonstring);

//            StandardUserCredFacade credFacade = new StandardUserCredFacade ();
            Contact con = userCredFacade.validate (name, password);
            
            JsonObject jsonObj = null;
            
            if (con != null) {
//                jsonObj = ContactXMLWrapper.toJSON (con);
//                System.out.println ("$$$$$$$$$$$$$ Contact ditemukan. ID: " + con.getSystemId () + " nama: " + con.getFirstName ());
            }
            else {
                jsonObj = buildFailedJSON();
//                System.out.println ("$$$$$$$$$$$$$ Contact TIDAK ditemukan");
            }

            //response.getWriter ().print (j.toJSONString ());
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter (stringWriter);
            jsonWriter.writeObject (jsonObj);
//            counter.setCount (counter.getCount () + 1);
//            
//            Util.log (Level.INFO, "Count: " + counter.getCount ());
            
//            System.out.println ("^^^^^^^^^^^^^^^^^^^^^^^^^^^ RESPON JSON:\n" + stringWriter.toString ());
            
            response.getWriter ().println (stringWriter.getBuffer ().toString ()); 
        }
        catch (Exception ex) {
//            System.out.println ("############## ERROR di method processRequest");
            ex.printStackTrace (System.out);
        }
    }
    
    private JsonObject buildFailedJSON ()
    {
        JsonObject jsonObj = Json.createObjectBuilder()
                .add("failReason", "Can't find contact")
                .build();
        
        return jsonObj;
    }

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
        System.out.println ("#$$#$#$#$#$##$#$$#$$##$$#$#$# doGET dipanggil ");
        processRequest (request, response);
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
        System.out.println ("#$$#$#$#$#$##$#$$#$$##$$#$#$# doPost dipanggil ");
        processRequest (request, response);
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
