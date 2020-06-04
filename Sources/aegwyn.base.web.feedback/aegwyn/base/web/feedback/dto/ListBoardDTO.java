/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ListFAQDTO.java
 *
 * Created on May 31, 2017, 1:36:26 PM
 */

package aegwyn.base.web.feedback.dto;

import aegwyn.base.web.dto.StandardDTO;
import aegwyn.core.feedback.model.entity.Board;
import aegwyn.core.feedback.model.entity.FAQ;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListBoardDTO extends StandardDTO
{
    private List<BoardDTO> listBoards = new LinkedList<>();
    
     /**
     * @return the listFAQ
     */
    public List<BoardDTO> getListBoard ()
    {
        return listBoards;
    }

    /**
     * @param listFAQ the listFAQ to set
     */
    public void setListBoard (List<BoardDTO> listFAQ)
    {
        this.listBoards = listFAQ;
    }
    
    public void setData(List<Board> _boards) {
        for (Board board : _boards) {
            BoardDTO boardDTO = new BoardDTO ();
            boardDTO.setData (board);
            listBoards.add (boardDTO);
        }
    }
}
