/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ApplicationContext.java
 *
 * Created on Apr 11, 2017, 11:48:50 AM
 */

package aegwyn.core.web.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Benny
 */
@ApplicationScoped
public class WebApplicationContext 
{
    @Inject
    WebRequestInfo webRequestInfo;
    
    private Properties runtimeProperties;
    
    public WebApplicationContext() {
        readProperties();
    }
    
    public WebRequestInfo getWebRequestInfo()
    {
        return webRequestInfo;
    }
    
    public void setWebRequestInfo(WebRequestInfo _info) {
        webRequestInfo = _info;
    }
    
    public String getProperty(String _key) {
        return runtimeProperties.getProperty (_key);
    }
    
    private void readProperties() {
        runtimeProperties = new Properties ();
        InputStream input = null;
        
       try {
            URL url = this.getClass().getClassLoader().getResource("META-INF/config.properties");
            String path = "";
            if(url != null)
                path = url.getPath ();
            else
                return;
            
            input = new FileInputStream(path);
            runtimeProperties.load(input);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
            if (input != null) {
                try {
                        input.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
	}
    }
}
