package core.Rules;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Color;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class endGameTest {

    private final EndGameRule endGameRule = new EndGameRule();

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
    public void testGetStartingCellsEmpty() {
        Board board = new Board(11);
        assertFalse(endGameRule.getStartingCells(board, Color.black).findAny().isPresent());
    }

    @Test
    public void testGetStartingCellsForBlack() {
        Board board = new Board(11);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(4, 0), Color.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(3, 0)) };
        assertArrayEquals(expected, endGameRule.getStartingCells(board, Color.black).toArray());
    }

    @Test
    public void testGetStartingCellsForWhite() {
        Board board = new Board(11);
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(0, 9), Color.white);
        board.setCell(new Point(0, 5), Color.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(0, 5)),
                                                        board.getCell(new Point(0, 9)) };
        assertArrayEquals(expected, endGameRule.getStartingCells(board, Color.white).toArray());
    }

    @Test
    public void testEndGameRuleIsValidForBlack() {
        Board board = getTestBoard();
        assertTrue(endGameRule.isValid(board, Color.black));
    }

    @Test
    public void testEndGameRuleIsInvalidForWhite() {
        Board board = getTestBoard();
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
    public void testEndGameRuleIsValidForWhite() {
        Board board = getTestBoard();
        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(3, 2), Color.white);
        assertTrue(endGameRule.isValid(board, Color.white));
    }

    @Test
    public void testEndGameRuleIsInvalidForBlack() {
        Board board = getTestBoard();
        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(3, 2), Color.white);
        assertFalse(endGameRule.isValid(board, Color.black));
    }

}
