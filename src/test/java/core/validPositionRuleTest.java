package core;

import org.junit.Test;
import java.awt.*;

import static org.junit.Assert.*;

public class validPositionRuleTest {

    @Test
    public void hasStrongNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty());
        board.setCell(new Point(3, 3), Color.white);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty());
        board.setCell(new Point(1, 3), Color.black);
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


    @Test
    public void emptyPositionRule() {
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();

        board.setCell(new Point(1, 2), Color.black);

        Point point = new Point(1, 2);
        assertFalse(supervisor.newMove(point));
    }

    @Test
    public void crosscutRuleTest() {
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();
        Point point = new Point(2, 3);

        board.setCell(new Point(1, 2), Color.black);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(1, 3), Color.white);

        assertFalse(supervisor.newMove(point));
    }

    @Test
    public void weakRuleTest() {
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();
        Point point = new Point(1, 2);

        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(3, 3), Color.black);

        board.setCell(new Point(2, 0), Color.white);
        board.setCell(new Point(3, 0), Color.white);
        board.setCell(new Point(3, 1), Color.white);

        assertTrue(supervisor.newMove(point));
    }

    @Test
    public void validPositionRuleClassTest() {
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();

        // Checkerboard setting.
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);

        // Legal example move.
        Point point1 = new Point(1, 1);
        assertTrue(supervisor.newMove(point1));

        // Illegal example move.
        Point point2 = new Point(1, 0);
        assertFalse(supervisor.newMove(point2));

    }
}
