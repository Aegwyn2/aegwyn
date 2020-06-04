/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserSession.java
 *
 * Created on Mar 31, 2017, 11:57:54 AM
 */

package aegwyn.core.web.model;

import aegwyn.core.web.util.Util;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Benny
 */
public class UserSession
{
    
    private String sessionId;
    private Calendar lastActivity;
    private String sessionName;

    /**
     * @return the sessionName
     */
    public String getSessionName ()
    {
        return sessionName;
    }

    /**
     * @param sessionName the sessionName to set
     */
    public void setSessionName (String sessionName)
    {
        this.sessionName = sessionName;
    }
        
    private Map<String, Object> objects = new HashMap<>();
    
//    private Map<String, Object> parameters = new HashMap<> ();
    
    public UserSession()
    {
//        sessionId = Util.generateMD5 (Calendar.getInstance ().getTime ().toString ());
        sessionId = newSessionId ();
    }

    /**
     * @return the lastActivity
     */
    public Calendar getLastActivity ()
    {
        return lastActivity;
    }

    /**
     * @param lastActivity the lastActivity to set
     */
    public void setLastActivity (Calendar lastActivity)
    {
        this.lastActivity = lastActivity;
    }
    
    public Object getObject(String _key) 
    {
        return objects.get (_key);
    }
    
    public void addObject(String _key, Object _value) 
    {
        objects.put (_key, _value);
    }
    
    public void setSessionId(String _id) {
        sessionId = _id;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId ()
    {
        return sessionId;
    }
    
    public String newSessionId() {
        Calendar cal = Calendar.getInstance ();
        String year = String.format ("%04d", cal.get(Calendar.YEAR));
        String month = String.format("%02d", cal.get(Calendar.MONTH));
        String date = String.format("%02d", cal.get(Calendar.DATE));
        String hour = String.format("%02d", cal.get(Calendar.HOUR));
        String minute = String.format("%02d", cal.get(Calendar.MINUTE));
        String sec = String.format("%02d", cal.get(Calendar.SECOND));
        String mili = String.format("%03d", cal.get(Calendar.MILLISECOND));
        
        String sessionId = "" + mili + sec + minute + hour + date + month + year;
        return sessionId;
    }
    
    public Calendar decodeSessionId(String _s) throws Exception {
        if(_s.length () != 17)
            throw new Exception ("ERROR, SESSION ID STRING SIZE IS WRONG");
        
        Integer year = Integer.valueOf (_s.substring (13));
        Integer month = Integer.valueOf (_s.substring (11, 13));
        Integer date = Integer.valueOf (_s.substring (9, 11));
        Integer hour = Integer.valueOf (_s.substring (7, 9));
        Integer min = Integer.valueOf (_s.substring (5, 7));
        Integer sec = Integer.valueOf (_s.substring (3, 5));
        Integer mili = Integer.valueOf (_s.substring (0, 3));
       
        Calendar cal = Calendar.getInstance ();
        cal.set (Calendar.YEAR, year);
        cal.set (Calendar.MONTH, month);
        cal.set (Calendar.DATE, date);
        cal.set (Calendar.HOUR, hour);
        cal.set (Calendar.MINUTE, min);
        cal.set (Calendar.SECOND, sec);
        cal.set (Calendar.MILLISECOND, mili);
        
        return cal;
    }
    
    

    /**
     * @return the parameters
     */
//    public Map<String, Object> getParameters ()
//    {
//        return parameters;
//    }
//
//    /**
//     * @param parameters the parameters to set
//     */
//    public void setParameters (Map<String, Object> parameters)
//    {
//        this.parameters = parameters;
//    }
}
