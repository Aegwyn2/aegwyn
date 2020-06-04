/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * CounterTest.java
 *
 * Created on Mar 29, 2017, 3:49:52 PM
 */

package aegwyn.core.web.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Benny
 */
@SessionScoped
public class CounterTest implements Serializable
{
    private int count = 0;

    /**
     * @return the count
     */
    public int getCount ()
    {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount (int count)
    {
        this.count = count;
    }
    
    public int addCount()
    {
        return count++;
    }
    
}
