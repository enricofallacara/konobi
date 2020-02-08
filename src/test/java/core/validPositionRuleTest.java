package core;

import core.Entities.Board;
import core.Entities.Color;
import core.Entities.Supervisor;
import core.Rules.ValidPositionRule;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class validPositionRuleTest {
    private Supervisor supervisor = new Supervisor(11);
    private ValidPositionRule validPositionRule = new ValidPositionRule();

    private void setTestBoard(Board board) {
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);
    }

    /*@Test
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
    }*/

    @Test
    public void testValidPositionRule() {
        setTestBoard(supervisor.getBoard());
        supervisor.setCurrentPoint(new Point(1, 1));
        assertTrue(validPositionRule.isValid(supervisor));
    }

    @Test
    public void testValidPositionRuleAgain() {
        setTestBoard(supervisor.getBoard());
        supervisor.setCurrentPoint(new Point(1, 0));
        assertFalse(validPositionRule.isValid(supervisor));
    }

}
