package core.Rules;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Colour;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class endGameTest {

    private final EndGameRule endGameRule = new EndGameRule();

    private Board getTestBoard() {
        Board board = new Board(4);

        board.setCell(new Point(1, 0), Colour.black);
        board.setCell(new Point(2, 1), Colour.black);
        board.setCell(new Point(2, 2), Colour.black);
        board.setCell(new Point(2, 3), Colour.black);
        board.setCell(new Point(3, 3), Colour.black);

        board.setCell(new Point(2, 0), Colour.white);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(1, 2), Colour.white);
        board.setCell(new Point(1, 3), Colour.white);

        return board;
    }

    @Test
    public void testGetStartingCellsEmpty() {
        Board board = new Board(11);
        assertFalse(board.getStartingCells(Colour.black).findAny().isPresent());
    }

    @Test
    public void testGetStartingCellsForBlack() {
        Board board = new Board(11);
        board.setCell(new Point(3, 0), Colour.black);
        board.setCell(new Point(4, 0), Colour.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(3, 0)) };
        assertArrayEquals(expected, board.getStartingCells(Colour.black).toArray());
    }

    @Test
    public void testGetStartingCellsForWhite() {
        Board board = new Board(11);
        board.setCell(new Point(0, 0), Colour.black);
        board.setCell(new Point(0, 9), Colour.white);
        board.setCell(new Point(0, 5), Colour.white);
        Cell[] expected = new Cell[] { board.getCell(new Point(0, 5)),
                                                        board.getCell(new Point(0, 9)) };
        assertArrayEquals(expected, board.getStartingCells(Colour.white).toArray());
    }

    @Test
    public void testEndGameRuleIsValidForBlack() {
        Board board = getTestBoard();
        assertTrue(endGameRule.isValid(board, Colour.black));
    }

    @Test
    public void testEndGameRuleIsInvalidForWhite() {
        Board board = getTestBoard();
        assertFalse(endGameRule.isValid(board, Colour.white));
    }

    @Test
    public void testEndGameRuleNone() {
        Board board = getTestBoard();
        board.setCell(new Point(2, 2), Colour.white);
        assertFalse(endGameRule.isValid(board, Colour.black));
        assertFalse(endGameRule.isValid(board, Colour.white));
    }

    @Test
    public void testEndGameRuleIsValidForWhite() {
        Board board = getTestBoard();
        board.setCell(new Point(0, 0), Colour.white);
        board.setCell(new Point(2, 2), Colour.white);
        board.setCell(new Point(3, 2), Colour.white);
        assertTrue(endGameRule.isValid(board, Colour.white));
    }

    @Test
    public void testEndGameRuleIsInvalidForBlack() {
        Board board = getTestBoard();
        board.setCell(new Point(0, 0), Colour.white);
        board.setCell(new Point(2, 2), Colour.white);
        board.setCell(new Point(3, 2), Colour.white);
        assertFalse(endGameRule.isValid(board, Colour.black));
    }

}
