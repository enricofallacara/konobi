package core;

import org.junit.Test;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class validPoisitionRuleTest {

    @Test
    public void hasStrongNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty());
        board.setCell(new Point(3, 3), Color.white);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty());
        board.setCell(new Point(1, 3), Color.black);
        ArrayList<Cell> test = board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty());
    }

    @Test
    public void hasWeakNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty());
        board.setCell(new Point(3, 4), Color.white);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty());
        board.setCell(new Point(1, 2), Color.black);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty());
    }

/*
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

    @Test
    public void specialWeakPositionTest() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        Point point = new Point(1, 2);

        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 1), Color.white);
        board.setCell(new Point(3, 3), Color.white);

        board.setCell(new Point(2, 0), Color.black);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(3, 1), Color.black);
        assertTrue(ValidPositionRule.specialWeakPosition(point, board, player));
    }

 */

    @Test
    public void crosscutRuleTest() {
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);

        board.setCell(new Point(1, 2), Color.black);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);

        CrosscutRule cross = new CrosscutRule();

        assertFalse(cross.isValid(point, board, player));
    }

    @Test
    public void weakRuleTest() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        Point point = new Point(1, 2);

        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 1), Color.white);
        board.setCell(new Point(3, 3), Color.white);

        board.setCell(new Point(2, 0), Color.black);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(3, 1), Color.black);

        WeakRule weak = new WeakRule();

        assertTrue(weak.isValid(point, board, player));
    }

    @Test
    public void validPositionRuleTest() {
        Board board = new Board(11);
        Player player = new Player(Color.black);

        PositionRule[] posRules = new PositionRule[]{new WeakRule(), new CrosscutRule()};

        // Checkerboard setting.
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);

        // Legal example move.
        Point point1 = new Point(1, 1);
        assertTrue(ValidPositionRule.query(posRules, point1, board, player));

        // Illegal example move.
        Point point2 = new Point(1, 0);
        assertFalse(ValidPositionRule.query(posRules, point2, board, player));

    }

}
