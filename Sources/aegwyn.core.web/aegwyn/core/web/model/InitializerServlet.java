/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * InitializerServlet.java
 *
 * Created on May 26, 2017, 1:32:04 PM
 */

package aegwyn.core.web.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 *
 * @author Benny
 */
public class InitializerServlet implements ServletContextListener
{

    @Override
    public void contextInitialized (ServletContextEvent _sce)
    {
//        System.out.println ("INIT CALLED ");
//        Properties prop = new Properties();
//	OutputStream output = null;
//        InputStream input = null;
//        
//       try {
//            String path = this.getClass().getClassLoader().getResource("META-INF/config.properties").getPath();
//            System.out.println ("PATH: " + path);
////            System.out.println(new File(".").getAbsolutePath());
//            
//            input = new FileInputStream(path);
//
//            // load a properties file
//            prop.load(input);
//
//            // get the property value and print it out
//            System.out.println("DATABASE: " + prop.getProperty("database"));
//
//	} catch (IOException ex) {
//		ex.printStackTrace();
//	} finally {
//            if (input != null) {
//                try {
//                        input.close();
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
//            }
//	}
        

//	try {
//             String path = this.getClass().getClassLoader().getResource("").getPath();
//        String fullPath = URLDecoder.decode(path, "UTF-8");
//        
//            System.out.println ("PATH: " + path);
//            System.out.println ("FULL PATH: " + fullPath);
//            output = new FileOutputStream(path + "META-INF/conf.properties");
//
//            // set the properties value
//            prop.setProperty("database", "localhost");
//            prop.setProperty("dbuser", "mkyong");
//            prop.setProperty("dbpassword", "password");
//
//            // save properties to project root folder
//            prop.store(output, null);
//
//	} catch (IOException io) {
//            io.printStackTrace();
//	} finally {
//            if (output != null) {
//                try {
//                    output.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//	}
    }

    @Override
    public void contextDestroyed (ServletContextEvent _sce)
    {
//        System.out.println ("DESTROYED CALLED");
//        Properties prop = new Properties();
//	InputStream input = null;
//        
//        try {
//            input = new FileInputStream("config.properties");
//
//            // load a properties file
//            prop.load(input);
//
//            // get the property value and print it out
//            System.out.println(prop.getProperty("database"));
//            System.out.println(prop.getProperty("dbuser"));
//            System.out.println(prop.getProperty("dbpassword"));
//
//	} catch (IOException ex) {
//		ex.printStackTrace();
//	} finally {
//            if (input != null) {
//                try {
//                        input.close();
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
//            }
//	}
    }

}
