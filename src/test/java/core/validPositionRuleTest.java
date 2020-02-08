package core;

import core.Entities.Board;
import core.Entities.Color;
import core.Entities.Player;
import core.Entities.Supervisor;
import core.Rules.CrosscutRule;
import core.Rules.ValidPositionRule;
import core.Rules.WeakRule;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class validPositionRuleTest {

    /*@Test
    public void hasStrongNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).findAny().isPresent());
        board.setCell(new Point(3, 3), Color.white);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).findAny().isPresent());
        board.setCell(new Point(1, 3), Color.black);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).findAny().isPresent());
    }

    @Test
    public void hasWeakNeighboursTest(){
        Board board = new Board(11);
        Player player = new Player(Color.black);
        Point point = new Point(2, 3);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).findAny().isPresent());
        board.setCell(new Point(3, 4), Color.white);
        assertFalse(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).findAny().isPresent());
        board.setCell(new Point(1, 2), Color.black);
        assertTrue(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).findAny().isPresent());
    }*/

    @Test
    public void weakRuleTest() {
        Board board = new Board(11);
        Player player = new Player(Color.white);
        Point point = new Point(1, 2);

        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 1), Color.white);
        board.setCell(new Point(3, 3), Color.white);

        board.setCell(new Point(2, 0), Color.black);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(3, 1), Color.black);

        assertTrue(WeakRule.isValid(point, board, player));
    }

    @Test
    public void validPositionRuleClassTest() {
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();

        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);

        ValidPositionRule rule1 = new ValidPositionRule();
        supervisor.setCurrentPoint(new Point(1, 1));
        assertTrue(rule1.isValid(supervisor));

        ValidPositionRule rule2 = new ValidPositionRule();
        supervisor.setCurrentPoint(new Point(1, 0));
        assertFalse(rule2.isValid(supervisor));
    }

}
