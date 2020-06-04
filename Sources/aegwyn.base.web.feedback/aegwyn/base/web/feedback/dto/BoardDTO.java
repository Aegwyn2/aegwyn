/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FAQDTO.java
 *
 * Created on May 30, 2017, 11:35:08 AM
 */

package aegwyn.base.web.feedback.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.feedback.model.entity.Board;
import aegwyn.core.feedback.model.entity.FAQ;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardDTO extends StandardDTO
{
    private Long systemId;
    private String name;
    private String content;
    
    
    public void setData(Board _board) {
        systemId = _board.getSystemId ();
        name = _board.getName ();
        content = _board.getContent ();
        if(_board.getTenant () != null)
            setData (_board.getTenant ());
        else
            setTenantId (_board.getTenantId ());
    }
    
    /**
     * @return the systemId
     */
    public Long getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (Long systemId)
    {
        this.systemId = systemId;
    }
    
    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * @return the content
     */
    public String getContent ()
    {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent (String content)
    {
        this.content = content;
    }
    
}
