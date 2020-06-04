/**
 * Util.java
 *
 * Created on Mar 22, 2017, 2:23:34 PM
 */
package aegwyn.core.web.util;

import java.io.BufferedInputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Benny
 */
public class Util
{
    private static Logger logger;
    public static final String LOG_OUTPUT_PREFIX = "%%%%%%%%%%%%%%%% ";
    
    public static Logger getLogger()
    {
        if (logger == null) {
            logger = Logger.getLogger ("Aegwyn");
            logger.addHandler (new ConsoleHandler ());
        }
        
        return logger;
    }
    
    public static void log (Level _lvl, String _msg)
    {
        getLogger ().log (_lvl, LOG_OUTPUT_PREFIX + _msg);
    }
    
    private static final String ENCODING = "UTF-8";

    /**
     *
     * @param _input
     * @return
     */
    public static String generateMD5 (String _input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance ("MD5");
            byte[] md5bytes = md.digest (_input.getBytes (Charset.forName (ENCODING)));

            StringBuilder sb = new StringBuilder ();
            for (byte b : md5bytes) {
                sb.append (String.format ("%02x", b & 0xFF));
            }

            return sb.toString ();
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace (System.out);
            return null;
        }
    }

    public static UserAgentInfo extractUserAgentInfo (HttpServletRequest _req) throws Exception
    {
        UserAgentInfo ret = new UserAgentInfo ();

        ret.browserDetails = _req.getHeader ("User-Agent");
        ret.userAgent = ret.browserDetails;
        ret.user = ret.userAgent.toLowerCase ();
        ret.os = "";
        ret.browser = "";
        String ipAddress = _req.getHeader("X-FORWARDED-FOR");  
        if (ipAddress == null) {  
         ipAddress = _req.getRemoteAddr();  
        }
        ret.ipAddress = ipAddress;

        //=================OS=======================
        String uaLowerCase = ret.userAgent.toLowerCase ();
        if (uaLowerCase.indexOf ("windows") > -1) {
            ret.os = "Windows";
        }
        else if (uaLowerCase.indexOf ("mac") > -1) {
            ret.os = "Mac";
        }
        else if (uaLowerCase.indexOf ("x11") > -1) {
            ret.os = "Unix";
        }
        else if (uaLowerCase.indexOf ("android") > -1) {
            ret.os = "Android";
        }
        else if (uaLowerCase.indexOf ("iphone") > -1) {
            ret.os = "iPhone";
        }
        else {
            ret.os = "UnKnown, More-Info: " + ret.userAgent;
        }
        
        //===============Browser===========================
        if (ret.user.contains ("msie")) 
        {
            String[] arr = ret.userAgent.substring (ret.userAgent.indexOf ("MSIE")).split (";");
            String substring = arr[0];
            
            if (arr.length > 1)
                ret.browser = substring.split (" ")[0].replace ("MSIE", "IE") + "-" + substring.split (" ")[1];
            else
                ret.browser = substring;
        }
        else if (ret.user.contains ("safari") && ret.user.contains ("version")) 
        {
            ret.browser = (ret.userAgent.substring (ret.userAgent.indexOf ("Safari")).split (" ")[0]).split ("/")[0] + "-" + (ret.userAgent.substring (ret.userAgent.indexOf ("Version")).split (" ")[0]).split ("/")[1];
        }
        else if (ret.user.contains ("opr") || ret.user.contains ("opera"))
        {
            if (ret.user.contains ("opera"))
                ret.browser = (ret.userAgent.substring (ret.userAgent.indexOf ("Opera")).split (" ")[0]).split ("/")[0] + "-" + (ret.userAgent.substring (ret.userAgent.indexOf ("Version")).split (" ")[0]).split ("/")[1];
            else if (ret.user.contains ("opr"))
                ret.browser = ((ret.userAgent.substring (ret.userAgent.indexOf ("OPR")).split (" ")[0]).replace ("/", "-")).replace ("OPR", "Opera");
        }
        else if (ret.user.contains ("chrome"))
        {
            ret.browser = (ret.userAgent.substring (ret.userAgent.indexOf ("Chrome")).split (" ")[0]).replace ("/", "-");
        }
        else if ((ret.user.indexOf ("mozilla/7.0") > -1) || (ret.user.indexOf ("netscape6") > -1) || (ret.user.indexOf ("mozilla/4.7") > -1) || (ret.user.indexOf ("mozilla/4.78") > -1) || (ret.user.indexOf ("mozilla/4.08") > -1) || (ret.user.indexOf ("mozilla/3") > -1))
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            ret.browser = "Netscape-?";

        }
        else if (ret.user.contains ("firefox")) 
        {
            ret.browser = (ret.userAgent.substring (ret.userAgent.indexOf ("Firefox")).split (" ")[0]).replace ("/", "-");
        }
        else if (ret.user.contains ("rv"))
        {
            ret.browser = "IE-" + ret.user.substring (ret.user.indexOf ("rv") + 3, ret.user.indexOf (")"));
        }
        else 
        {
            ret.browser = "UnKnown, More-Info: " + ret.userAgent;
        }
        
        return ret;
    }
    
    public static String getChecksumOf(String _strToCheck) 
    {
        // this getchecksum does not put : delimiters inside results
        try {
            StringBuffer buf = new StringBuffer();
            byte[] digestBuffer = new byte[8];
            byte[] digest = null;
            BufferedInputStream bis;
            MessageDigest md5;
            int i;
            md5 = MessageDigest.getInstance("MD5");
            md5.update(_strToCheck.getBytes());
            digest = md5.digest();
            int len = digest.length;
            for (int j = 0; j < len; j++) {
                byte2hex(digest[j], buf);
            }
            
            return buf.toString().toLowerCase ();
        } 
        catch (Exception e) {
            e.printStackTrace (System.out);
        }
        return null;
    }
    
    private static void byte2hex(byte b, StringBuffer buf) 
    {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int high = (b & 0xf0) >> 4;
        int low = b & 0x0f;
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
}
