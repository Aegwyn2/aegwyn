/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * GeneralDTO.java
 *
 * Created on Mar 31, 2017, 4:33:55 PM
 */

package aegwyn.base.web.dto;

import aegwyn.core.cred.entity.Tenant;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StandardDTO 
{
    private String sessionString;
    private String errorMessage;
    private String companyName;
    private String tenantId;
    private Integer locationNo;
    private Integer locationTenantAmount;
    
    public void setData(Tenant _tenant) {
        tenantId = _tenant.getTenantId ();
        companyName = _tenant.getCompanyName ();
        locationNo = _tenant.getTenantLocation ().getNo ();
        locationTenantAmount = _tenant.getTenantLocation ().getAmount ();
    }

    /**
     * @return the session
     */
    public String getSessionString ()
    {
        return sessionString;
    }

    /**
     * @param session the session to set
     */
    public void setSessionString (String session)
    {
        this.sessionString = session;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage ()
    {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
    
    public void setTenantId(String _id)
    {
        tenantId = _id;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId ()
    {
        return tenantId;
    }
    
    /**
     * @return the locationNo
     */
    public Integer getLocationNo ()
    {
        return locationNo;
    }

    /**
     * @param tenantNo the locationNo to set
     */
    public void setLocationNo (Integer tenantNo)
    {
        this.locationNo = tenantNo;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName ()
    {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * @return the locationTenantAmount
     */
    public Integer getLocationTenantAmount ()
    {
        return locationTenantAmount;
    }

    /**
     * @param locationTenantAmount the locationTenantAmount to set
     */
    public void setLocationTenantAmount (Integer locationTenantAmount)
    {
        this.locationTenantAmount = locationTenantAmount;
    }
}

