/**
 * TenantInfo.java
 *
 * Created on Apr 11, 2017, 11:47:33 AM
 */
package aegwyn.core.web.model;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Benny
 */
@RequestScoped
public class WebRequestInfo 
{
    private String persistenceJNDILocation;
//    private String tenantId;

    /**
     * @return the jndiLocation
     */
    public String getPersistenceJNDILocation ()
    {
        return persistenceJNDILocation;
    }

    /**
     * @param jndiLocation the jndiLocation to set
     */
    public void setPersistenceJNDILocation (String jndiLocation)
    {
        this.persistenceJNDILocation = jndiLocation;
    }
    
//    /**
//     * @return the tenantId
//     */
//    public String getTenantId ()
//    {
//        return tenantId;
//    }
//
//    /**
//     * @param tenantId the tenantId to set
//     */
//    public void setTenantId (String tenantId)
//    {
//        this.tenantId = tenantId;
//    }
}
