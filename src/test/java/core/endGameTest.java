package core;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class endGameTest {

    @Test
    public void testGetEndpoints() {
        Board board = new Board(11);
        Player player = new Player(Color.black);
        EndGameRule endGameRule = new EndGameRule();

        assertTrue(endGameRule.getStartingPoints(board, player).isEmpty());
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(4, 0), Color.white);
        ArrayList<Cell> expected = new ArrayList<>();
        expected.add(board.getCell(new Point(3, 0)));
        assertEquals(expected, endGameRule.getStartingPoints(board, player));
    }

    @Test
    public void testSearchForEndingEdge() {
        Board board = new Board(4);
        Player player = new Player(Color.black);
        EndGameRule endGameRule = new EndGameRule();
        Point point = new Point(1, 0);

        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(2, 2), Color.black);
        board.setCell(new Point(2, 3), Color.black);
        board.setCell(new Point(3, 3), Color.black);

        board.setCell(new Point(2, 0), Color.white);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);

        assertTrue(endGameRule.searchForEndingEdge(board.getCell(point), board, player));

        board.setCell(new Point(2, 2), Color.white);
        assertFalse(endGameRule.searchForEndingEdge(board.getCell(point), board, player));
    }
    @Test
    public void testQuery() {
        Board board = new Board(4);
        Player black = new Player(Color.black);
        Player white = new Player(Color.white);
        EndGameRule endGameRule = new EndGameRule();

        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(2, 2), Color.black);
        board.setCell(new Point(2, 3), Color.black);
        board.setCell(new Point(3, 3), Color.black);

        board.setCell(new Point(2, 0), Color.white);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);

        assertTrue(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));

        board.setCell(new Point(2, 2), Color.white);
        assertFalse(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));
    }
}
