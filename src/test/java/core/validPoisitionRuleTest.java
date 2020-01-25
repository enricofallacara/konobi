package core;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class validPoisitionRuleTest {

    @Test
    public void hasStrongNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point p = new Point(2, 3);
        assertFalse(ValidPositionRule.hasStrong(p, board, player));
        board.setCell(new Point(3, 3), Color.white);
        assertFalse(ValidPositionRule.hasStrong(p, board, player));
        board.setCell(new Point(1, 3), Color.black);
        assertTrue(ValidPositionRule.hasStrong(p, board, player));
    }

    @Test
    public void hasWeakNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point p = new Point(2, 3);
        assertFalse(ValidPositionRule.hasWeak(p, board, player));
        board.setCell(new Point(3, 4), Color.white);
        assertFalse(ValidPositionRule.hasWeak(p, board, player));
        board.setCell(new Point(1, 2), Color.black);
        assertTrue(ValidPositionRule.hasWeak(p, board, player));
    }
}
