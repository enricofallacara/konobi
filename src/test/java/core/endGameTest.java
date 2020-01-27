package core;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class endGameTest {

    @Test
    public void testGetEndpoints() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        assertTrue(EndGameRule.getEndPoints(board, player).isEmpty());
        board.setCell(new Point(3, 0), Color.white);
        board.setCell(new Point(4, 0), Color.black);
        ArrayList<Cell> expected = new ArrayList<>();
        expected.add(board.getCell(new Point(3, 0)));
        assertEquals(expected, EndGameRule.getEndPoints(board, player));
    }
}
