package core;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class validPoisitionRuleTest {

    @Test
    public void hasStrongNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertFalse(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getStrongNeighbours));
        board.setCell(new Point(3, 3), Color.white);
        assertFalse(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getStrongNeighbours));
        board.setCell(new Point(1, 3), Color.black);
        assertTrue(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getStrongNeighbours));
    }

    @Test
    public void hasWeakNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertFalse(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getWeakNeighbours));
        board.setCell(new Point(3, 4), Color.white);
        assertFalse(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getWeakNeighbours));
        board.setCell(new Point(1, 2), Color.black);
        assertTrue(ValidPositionRule.hasCertainNeighbours(point, board, player, board::getWeakNeighbours));
    }


    @Test
    public void crosscutTest() {
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        board.setCell(new Point(1, 2), Color.black);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);
        assertTrue(ValidPositionRule.hasCrosscut(point, board, player));

    }
}
