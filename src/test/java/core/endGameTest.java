package core;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Color;
import core.Entities.Player;
import core.Rules.EndGameRule;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;

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
        Player player = new Player(Color.black);
        assertTrue(endGameRule.getStartingPoints(board, player).isEmpty());
    }

    @Test
    public void testGetStartingPointsForBlack() {
        Board board = new Board(11);
        Player player = new Player(Color.black);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(4, 0), Color.white);
        ArrayList<Cell> expected = new ArrayList<>() {{ add(board.getCell(new Point(3, 0))); }};
        assertEquals(expected, endGameRule.getStartingPoints(board, player));
    }

    @Test
    public void testGetStartingPointsForWhite() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(0, 9), Color.white);
        board.setCell(new Point(0, 5), Color.white);
        ArrayList<Cell> expected = new ArrayList<>() {{ add(board.getCell(new Point(0, 5)));
                                                        add(board.getCell(new Point(0, 9))); }};
        assertEquals(expected, endGameRule.getStartingPoints(board, player));
    }

    @Test
    public void testSearchForEndingEdge() {
        Board board = getTestBoard();
        Player player = new Player(Color.black);
        Point point = new Point(1, 0);
        assertTrue(endGameRule.searchForEndingEdge(board.getCell(point), board, player));
        assertFalse(endGameRule.searchForEndingEdge(board.getCell(point), board, player));
    }

    @Test
    public void testEndGameRuleForBlack() {
        Board board = getTestBoard();
        Player black = new Player(Color.black);
        Player white = new Player(Color.white);
        assertTrue(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));
    }

    @Test
    public void testEndGameRuleNone() {
        Board board = getTestBoard();
        Player black = new Player(Color.black);
        Player white = new Player(Color.white);
        board.setCell(new Point(2, 2), Color.white);
        assertFalse(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));
    }

    @Test
    public void testEndGameRuleForWhite() {
        Board board = getTestBoard();
        Player black = new Player(Color.black);
        Player white = new Player(Color.white);
        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(3, 2), Color.white);
        assertTrue(endGameRule.isValid(board, white));
        assertFalse(endGameRule.isValid(board, black));
    }

}
