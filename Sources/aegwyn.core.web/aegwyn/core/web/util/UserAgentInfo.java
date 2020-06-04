/**
 * UserAgentInfo.java
 *
 * Created on Mar 22, 2017, 3:32:28 PM
 */

package aegwyn.core.web.util;

/**
 *
 * @author Benny
 * @author Irfin
 */
public class UserAgentInfo 
{
    public String browserDetails;
    public String userAgent;
    public String user;
    public String os;
    public String browser;
    public String ipAddress;
    
    @Override
    public String toString ()
    {
        StringBuilder ret = new StringBuilder ();
        ret.append ("Browser Details: ").append (browserDetails).append ('\n');
        ret.append ("User-Agent: ").append (userAgent).append ('\n');
        ret.append ("User: ").append (user).append ('\n');
        ret.append ("OS: ").append (os).append ('\n');
        ret.append ("Browser: ").append (browser).append ('\n');
        ret.append ("IP: ").append (ipAddress);
        
        return ret.toString ();
    }
}
