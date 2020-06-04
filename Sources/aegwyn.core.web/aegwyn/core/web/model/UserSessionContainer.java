/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserSessionContainer.java
 *
 * Created on May 26, 2017, 12:52:01 PM
 */

package aegwyn.core.web.model;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Benny
 */
@ApplicationScoped
public interface UserSessionContainer {
    public UserSession newSession();
    public UserSession getSession(String _sessionId, boolean _createNew);
    public boolean removeSession(String _sessionId);
    
}
