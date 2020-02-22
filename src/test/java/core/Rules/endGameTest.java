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

        board.setCellColour(new Point(1, 0), Colour.BLACK);
        board.setCellColour(new Point(2, 1), Colour.BLACK);
        board.setCellColour(new Point(2, 2), Colour.BLACK);
        board.setCellColour(new Point(2, 3), Colour.BLACK);
        board.setCellColour(new Point(3, 3), Colour.BLACK);

        board.setCellColour(new Point(2, 0), Colour.WHITE);
        board.setCellColour(new Point(1, 1), Colour.WHITE);
        board.setCellColour(new Point(1, 2), Colour.WHITE);
        board.setCellColour(new Point(1, 3), Colour.WHITE);

        return board;
    }

    @Test
    public void testGetStartingCellsEmpty() {
        Board board = new Board(11);
        assertFalse(board.getStartingCellsForColour(Colour.BLACK).findAny().isPresent());
    }

    @Test
    public void testGetStartingCellsForBlack() {
        Board board = new Board(11);
        board.setCellColour(new Point(3, 0), Colour.BLACK);
        board.setCellColour(new Point(4, 0), Colour.WHITE);
        Cell[] expected = new Cell[] { board.getCell(new Point(3, 0)) };
        assertArrayEquals(expected, board.getStartingCellsForColour(Colour.BLACK).toArray());
    }

    @Test
    public void testGetStartingCellsForWhite() {
        Board board = new Board(11);
        board.setCellColour(new Point(0, 0), Colour.BLACK);
        board.setCellColour(new Point(0, 9), Colour.WHITE);
        board.setCellColour(new Point(0, 5), Colour.WHITE);
        Cell[] expected = new Cell[] { board.getCell(new Point(0, 5)),
                                                        board.getCell(new Point(0, 9)) };
        assertArrayEquals(expected, board.getStartingCellsForColour(Colour.WHITE).toArray());
    }

    @Test
    public void testEndGameRuleIsValidForBlack() {
        Board board = getTestBoard();
        assertTrue(endGameRule.isValid(board, Colour.BLACK));
    }

    @Test
    public void testEndGameRuleIsInvalidForWhite() {
        Board board = getTestBoard();
        assertFalse(endGameRule.isValid(board, Colour.WHITE));
    }

    @Test
    public void testEndGameRuleNone() {
        Board board = getTestBoard();
        board.setCellColour(new Point(2, 2), Colour.WHITE);
        assertFalse(endGameRule.isValid(board, Colour.BLACK));
        assertFalse(endGameRule.isValid(board, Colour.WHITE));
    }

    @Test
    public void testEndGameRuleIsValidForWhite() {
        Board board = getTestBoard();
        board.setCellColour(new Point(0, 0), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);
        board.setCellColour(new Point(3, 2), Colour.WHITE);
        assertTrue(endGameRule.isValid(board, Colour.WHITE));
    }

    @Test
    public void testEndGameRuleIsInvalidForBlack() {
        Board board = getTestBoard();
        board.setCellColour(new Point(0, 0), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);
        board.setCellColour(new Point(3, 2), Colour.WHITE);
        assertFalse(endGameRule.isValid(board, Colour.BLACK));
    }

}
