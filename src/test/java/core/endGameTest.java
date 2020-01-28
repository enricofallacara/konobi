package core;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class endGameTest {

    @Test
    public void testGetEndpoints() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        assertTrue(EndGameRule.getStartingPoints(board, player).isEmpty());
        board.setCell(new Point(3, 0), Color.white);
        board.setCell(new Point(4, 0), Color.black);
        ArrayList<Cell> expected = new ArrayList<>();
        expected.add(board.getCell(new Point(3, 0)));
        assertEquals(expected, EndGameRule.getStartingPoints(board, player));
    }

    @Test
    public void testSearchForEndingEdge() {
        Board board = new Board(4);
        Player player = new Player(Color.black);
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

        assertTrue(EndGameRule.searchForEndingEdge(board.getCell(point), board, player));

        board.setCell(new Point(2, 2), Color.white);
        assertFalse(EndGameRule.searchForEndingEdge(board.getCell(point), board, player));
    }
}
