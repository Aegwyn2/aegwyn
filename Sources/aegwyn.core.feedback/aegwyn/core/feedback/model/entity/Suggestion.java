/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Question.java
 *
 * Created on May 26, 2017, 11:15:48 AM
 */

package aegwyn.core.feedback.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Benny
 */
@Entity
@Table(name="suggestion")
public class Suggestion extends Feedback
{
    @ManyToOne
    @JoinColumn(name="idboard")
    private SuggestionBoard suggestionBoard;
    
     /**
     * @return the suggestionBoard
     */
    public SuggestionBoard getSuggestionBoard ()
    {
        return suggestionBoard;
    }

    /**
     * @param suggestionBoard the suggestionBoard to set
     */
    public void setSuggestionBoard (SuggestionBoard suggestionBoard)
    {
        this.suggestionBoard = suggestionBoard;
    }
}
