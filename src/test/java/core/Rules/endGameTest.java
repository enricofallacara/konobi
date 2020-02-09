package core.Rules;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Color;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class endGameTest {
    private EndGameRule endGameRule = new EndGameRule();

    private Board getTestBoard() {
        Board board = new Board(4);

        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(2, 2), Color.black);
        board.setCell(new Point(2, 3), Color.black);
        board.setCell(new Point(3, 3), Color.black);

        board.setCell(new Point(2, 0), Color.white);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);

        return board;
    }

    @Test
    public void testGetStartingPointsEmpty() {
        Board board = new Board(11);
        assertFalse(endGameRule.getStartingPoints(board, Color.black).findAny().isPresent());
    }

    @Test
    public void testGetStartingPointsForBlack() {
        Board board = new Board(11);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(4, 0), Color.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(3, 0)) };
        assertArrayEquals(expected, endGameRule.getStartingPoints(board, Color.black).toArray());
    }

    @Test
    public void testGetStartingPointsForWhite() {
        Board board = new Board(11);
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(0, 9), Color.white);
        board.setCell(new Point(0, 5), Color.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(0, 5)),
                                                        board.getCell(new Point(0, 9)) };
        assertArrayEquals(expected, endGameRule.getStartingPoints(board, Color.white).toArray());
    }

    @Test
    public void testEndGameRuleForBlack() {
        Board board = getTestBoard();
        assertTrue(endGameRule.isValid(board, Color.black));
        assertFalse(endGameRule.isValid(board, Color.white));
    }

    @Test
    public void testEndGameRuleNone() {
        Board board = getTestBoard();
        board.setCell(new Point(2, 2), Color.white);
        assertFalse(endGameRule.isValid(board, Color.black));
        assertFalse(endGameRule.isValid(board, Color.white));
    }

    @Test
    public void testEndGameRuleForWhite() {
        Board board = getTestBoard();
        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(3, 2), Color.white);
        assertTrue(endGameRule.isValid(board, Color.white));
        assertFalse(endGameRule.isValid(board, Color.black));
    }

}
