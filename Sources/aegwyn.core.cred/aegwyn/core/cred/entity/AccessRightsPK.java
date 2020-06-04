/*
 * AccessRightsPK.java
 *
 * Created on Feb 6, 2015, 1:05:55 PM
 */
package aegwyn.core.cred.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author irfin
 */
public class AccessRightsPK implements Serializable
{
    private Integer owner;
    private Integer taskType;

    @Override
    public int hashCode ()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode (this.owner);
        hash = 89 * hash + Objects.hashCode (this.taskType);
        return hash;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;
        if (getClass () != obj.getClass ())
            return false;
        
        final AccessRightsPK that = (AccessRightsPK) obj;
        if (!Objects.equals (this.owner, that.owner))
            return false;
        if (!Objects.equals (this.taskType, that.taskType))
            return false;
        
        return true;
    }
}
