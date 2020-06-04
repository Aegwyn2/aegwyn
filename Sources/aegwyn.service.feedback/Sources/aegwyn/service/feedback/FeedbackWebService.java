/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FeedbackWebService.java
 *
 * Created on May 29, 2017, 10:29:23 AM
 */

package aegwyn.service.feedback;

import aegwyn.base.feedback.control.BoardFacade;
import aegwyn.base.feedback.control.ComplaintFacade;
import aegwyn.base.feedback.control.FAQFacade;
import aegwyn.base.feedback.control.FeedbackFacade;
import aegwyn.base.web.dto.StandardDTO;
import aegwyn.base.web.cred.dto.UserDTO;
import aegwyn.base.web.feedback.dto.BoardDTO;
import aegwyn.base.web.feedback.dto.ComplaintDTO;
import aegwyn.base.web.feedback.dto.FeedbackDTO;
import aegwyn.base.web.feedback.dto.ListBoardDTO;
import aegwyn.base.web.cred.interceptor.ExceptionMessager;
import aegwyn.base.web.cred.interceptor.LoginRequired;
import aegwyn.base.web.cred.model.ApplicationContext;
import aegwyn.core.cred.model.entity.RegisteredUser;
import aegwyn.core.cred.model.entity.Tenant;
import aegwyn.core.feedback.model.entity.Board;
import aegwyn.core.feedback.model.entity.Complaint;
import aegwyn.core.feedback.model.entity.ComplaintBoard;
import aegwyn.core.feedback.model.entity.Customer;
import aegwyn.core.feedback.model.entity.FAQ;
import aegwyn.core.feedback.model.entity.Feedback;
import aegwyn.core.feedback.model.entity.RequiredResponse;
import aegwyn.core.feedback.model.entity.Suggestion;
import aegwyn.core.feedback.model.entity.SuggestionBoard;
import aegwyn.core.web.model.UserSession;
import aegwyn.core.web.model.UserSessionContainer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Benny
 */
@Stateless
@Path("")
public class FeedbackWebService 
{    
    @EJB
    FAQFacade faqFacade;
    
    @EJB
    BoardFacade boardFacade;
    
    @EJB
    ComplaintFacade complaintFacade;
    
    @EJB
    FeedbackFacade feedbackFacade;
    
    @Inject
    ApplicationContext apc;
    
    @Inject
    UserSessionContainer usc;
    
    @GET                                   
    @Path("/boardlist/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionMessager
    @LoginRequired
    public ListBoardDTO getBoardList(@PathParam("type") String _type, @QueryParam("sessionString") String _string) throws Exception{
        ListBoardDTO retval = new ListBoardDTO (); 
        if(_string == null || _string.length () <= 0)
            retval.setErrorMessage ("ERROR, NO SESSION STRING SPECIFIED");
        else {
            Tenant tenant = apc.getCurrentTenant ();
            if(tenant == null)
                throw new Exception ("ERROR< NO TENANT FOr CURRENT REQUEST");
            List<? extends Board> listBoards = null;
            
            if(_type.equals ("faq")) 
                listBoards = boardFacade.findBoardByTenantId (tenant.getTenantId (), FAQ.class);
            else if(_type.equals ("complaint")) 
                listBoards = boardFacade.findBoardByTenantId (tenant.getTenantId (), ComplaintBoard.class);
            else if(_type.equals ("suggestion")) 
                listBoards = boardFacade.findBoardByTenantId (tenant.getTenantId (), SuggestionBoard.class);
            
            if(listBoards != null)
                retval.setData ((List<Board>)listBoards);
        }
        
        return retval;
    }
    
    @POST
    @Path("/newboard/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @ExceptionMessager
    @LoginRequired
    public StandardDTO addBoard(@PathParam("type") String _type, BoardDTO _dto) throws Exception {
        StandardDTO retval = new StandardDTO ();
        if(_dto.getName () == null || _dto.getName ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO BOARD NAME");
        else if(_dto.getContent ()== null || _dto.getContent ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO BOARD CONTENT");
        else {
            Tenant tenant = apc.getCurrentTenant ();
            if(tenant == null)
                throw new Exception("ERROR, NO TENANT FOR CURENT REQUEST");
            Board board = null;
            
            System.out.println ("Type: " + _type);
            
            if(_type.equals ("faq"))
                board = boardFacade.addBoard (tenant, _dto.getName (), _dto.getContent (), FAQ.class);
            else if(_type.equals ("complaint"))
                board = boardFacade.addBoard (tenant, _dto.getName (), _dto.getContent (), ComplaintBoard.class);
            else if(_type.equals ("suggestion"))
                board = boardFacade.addBoard (tenant, _dto.getName (), _dto.getContent (), SuggestionBoard.class);
            
            if(board == null)
                throw new Exception("ERROR, CAN't ADD NEW BOARD");
        }
        return retval;
    }
 
    @POST
    @Path("/editboard/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    @LoginRequired
    public StandardDTO editBoard(@PathParam("type") String _type, BoardDTO _board) throws Exception {
        StandardDTO retval = new StandardDTO ();
        if(_board.getSystemId () == null )
            retval.setErrorMessage ("ERROR, NO BOARD ID");
        else if(_board.getName () == null || _board.getName ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO BOARD NAME");
        else if(_board.getContent ()== null || _board.getContent ().length () <= 0)
            retval.setErrorMessage ("ERROR, NO BOARD CONTENT");
        else {
            Board board = null;
            if(_type.equals ("faq"))
                board = boardFacade.editBoard (_board.getSystemId (), _board.getName (), _board.getContent (), FAQ.class);
            else if(_type.equals ("complaint"))
                board = boardFacade.editBoard (_board.getSystemId (), _board.getName (), _board.getContent (), ComplaintBoard.class);
            else if(_type.equals ("suggestion"))
                board = boardFacade.editBoard (_board.getSystemId (), _board.getName (), _board.getContent (), SuggestionBoard.class);
            if(board == null)
                retval.setErrorMessage ("ERROR, CAN't EDIT THE BOARD");
        }
        return retval;
    }
 
    @POST
    @Path("/deleteboard/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ExceptionMessager
    @LoginRequired
    public StandardDTO deleteBoard(@PathParam("type") String _type, BoardDTO _faq) throws Exception {
        StandardDTO retval = new StandardDTO ();
        if(_faq.getSystemId () == null )
            retval.setErrorMessage ("ERROR, NO BOARD ID");
        else {
            Board board = null;
            if(_type.equals ("faq"))
                board = boardFacade.deleteBoard (_faq.getSystemId (), FAQ.class);
            else if(_type.equals ("complaint"))
                board = boardFacade.deleteBoard (_faq.getSystemId (), ComplaintBoard.class);
            else if(_type.equals ("suggestion"))
                board = boardFacade.deleteBoard (_faq.getSystemId (), SuggestionBoard.class);
            
            if(board == null)
                retval.setErrorMessage ("ERROR, CAN't DELETE THE BOARD");
        }
        return retval;
    }
    
    @POST
    @Path("/newcomplaint")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @ExceptionMessager
    @LoginRequired
    public StandardDTO addComplaint(ComplaintDTO _dto) throws Exception {
        System.out.println ("ADD COMPLAINT CALLED");
        StandardDTO retval = new StandardDTO ();
        if(_dto.getFeedbackContent () == null)
            retval.setErrorMessage ("ERROR, NO FEEDBACK CONTENT");
        else if(_dto.getFullName () == null)
            retval.setErrorMessage ("ERROR, NO CUSTOMER NAME");
        else if(_dto.getIdBoard () == null)
            retval.setErrorMessage ("ERROR, NO BOARD ID");
        else {
//            ComplaintBoard board = complaintFacade.findComplaintById (_dto.getIdBoard ());
            
            Complaint complaint = null;
            ComplaintBoard board = boardFacade.findBoardId (_dto.getIdBoard (), ComplaintBoard.class);
            if(board == null)
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED BOARD");

            complaint = new Complaint();
            complaint.setComplaintBoard (board);
            complaint.setFeedbackCall (_dto.getFeedbackCall ());
            complaint.setUrgentCall (_dto.getUrgentCall ());


            if(_dto.getRequiredResponseMemo () != null || _dto.getRequiredResponseDueDate () != null) {
                RequiredResponse rr = new RequiredResponse();
                rr.setTenantId (_dto.getTenantId ());
                rr.setDueDate (_dto.getRequiredResponseDueDate ());
                rr.setComplaint (complaint);
                complaint.setRequiredResponse (rr);
            }
            
            Customer cust = new Customer();
            cust.setTenantId (_dto.getTenantId ());
            cust.setGender (_dto.getGender ());
            cust.setFullName (_dto.getFullName ());
            cust.setAge (_dto.getAge ());
            cust.setAddrNation (_dto.getAddrNation ());
            cust.setAddrState (_dto.getAddrState ());
            cust.setAddrCity (_dto.getAddrCity ());
            cust.setAddrStreet (_dto.getAddrStreet ());
            cust.setAddrPhone (_dto.getAddrPhone ());
            cust.setEmail (_dto.getEmail ()); 

            complaint.setCustomer (cust);
            complaint.setTenantId (_dto.getTenantId ());
            complaint.setFeedbackContent (_dto.getFeedbackContent ());
            complaint.setEmailList (_dto.getEmailList ());
            complaint.setCustReaction (_dto.getCustReaction ());
            complaint.setHasAction (_dto.getHasAction ());
            complaint.setHasImage (_dto.getHasImage ());
            complaint.setIssueDate (_dto.getIssueDate ());
            complaint.setMapLocation (_dto.getMapLocation ());
            complaint.setMemo (_dto.getMemo ());
            complaint.setPriority (_dto.getPriority ());
            complaint.setSystemCreateDate (new Date());
            
            feedbackFacade.addFeedback (complaint);
        }
        
        return retval;
    }
    
    @POST
    @Path("/newsuggestion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @ExceptionMessager
    @LoginRequired
    public StandardDTO addSuggestion(FeedbackDTO _dto) throws Exception {
        StandardDTO retval = new StandardDTO ();
        if(_dto.getFeedbackContent () == null)
            retval.setErrorMessage ("ERROR, NO FEEDBACK CONTENT");
        else if(_dto.getFullName () == null)
            retval.setErrorMessage ("ERROR, NO CUSTOMER NAME");
        else if(_dto.getIdBoard () == null)
            retval.setErrorMessage ("ERROR< NO BOARD ID");
        else {
//            ComplaintBoard board = complaintFacade.findComplaintById (_dto.getIdBoard ());
            
            Suggestion suggestion = null;
            SuggestionBoard board = boardFacade.findBoardId (_dto.getIdBoard (), SuggestionBoard.class);
            if(board == null)
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED BOARD");

            suggestion = new Suggestion();
            suggestion.setSuggestionBoard (board);
//
//
//            if(_dto.getRequiredResponseMemo () != null || _dto.getRequiredResponseDueDate () != null) {
//                RequiredResponse rr = new RequiredResponse();
//                rr.setTenantId (_dto.getTenantId ());
//                rr.setDueDate (_dto.getRequiredResponseDueDate ());
//                rr.setComplaint (((Complaint)suggestion));
//                suggestion.setRequiredResponse (rr);
//            }
            
            Customer cust = new Customer();
            cust.setTenantId (_dto.getTenantId ());
            cust.setGender (_dto.getGender ());
            cust.setFullName (_dto.getFullName ());
            cust.setAge (_dto.getAge ());
            cust.setAddrNation (_dto.getAddrNation ());
            cust.setAddrState (_dto.getAddrState ());
            cust.setAddrCity (_dto.getAddrCity ());
            cust.setAddrStreet (_dto.getAddrStreet ());
            cust.setAddrPhone (_dto.getAddrPhone ());
            cust.setEmail (_dto.getEmail ()); 

            suggestion.setCustomer (cust);
            suggestion.setTenantId (_dto.getTenantId ());
            suggestion.setFeedbackContent (_dto.getFeedbackContent ());
            suggestion.setEmailList (_dto.getEmailList ());
            suggestion.setCustReaction (_dto.getCustReaction ());
            suggestion.setHasAction (_dto.getHasAction ());
            suggestion.setHasImage (_dto.getHasImage ());
            suggestion.setIssueDate (_dto.getIssueDate ());
            suggestion.setMapLocation (_dto.getMapLocation ());
            suggestion.setMemo (_dto.getMemo ());
            suggestion.setPriority (_dto.getPriority ());
            suggestion.setSystemCreateDate (new Date());
            
            feedbackFacade.addFeedback (suggestion);
        }
        
        return retval;
    }
 
}
