package core;

import core.Entities.Board;
import core.Entities.Color;
import core.Entities.Player;
import core.Rules.EndGameRule;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class endGameTest {

    @Test
    public void testGetEndpoints() {
        Board board = new Board(11);
        Player player = new Player(core.Entities.Color.black);
        EndGameRule endGameRule = new EndGameRule();

        assertFalse(endGameRule.getStartingPoints(board, player).findAny().isPresent());
        board.setCell(new Point(3, 0), core.Entities.Color.black);
        board.setCell(new Point(4, 0), core.Entities.Color.white);
        assertTrue(endGameRule.getStartingPoints(board, player).anyMatch(x -> x == board.getCell(new Point(3, 0))));

    }


    @Test
    public void testQuery() {
        Board board = new Board(4);
        Player black = new Player(core.Entities.Color.black);
        Player white = new Player(core.Entities.Color.white);
        EndGameRule endGameRule = new EndGameRule();

        board.setCell(new Point(1, 0), core.Entities.Color.black);
        board.setCell(new Point(2, 1), core.Entities.Color.black);
        board.setCell(new Point(2, 2), core.Entities.Color.black);
        board.setCell(new Point(2, 3), core.Entities.Color.black);
        board.setCell(new Point(3, 3), core.Entities.Color.black);

        board.setCell(new Point(2, 0), core.Entities.Color.white);
        board.setCell(new Point(1, 1), core.Entities.Color.white);
        board.setCell(new Point(1, 2), core.Entities.Color.white);
        board.setCell(new Point(1, 3), core.Entities.Color.white);

        assertTrue(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));
        board.setCell(new Point(2, 2), Color.white);
        assertFalse(endGameRule.isValid(board, black));
        assertFalse(endGameRule.isValid(board, white));
    }

}
