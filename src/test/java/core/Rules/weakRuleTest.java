package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class weakRuleTest {
    private final StatusSupervisor supervisor = new StatusSupervisor(5);
    private final Board board = supervisor.getBoard();
    private final WeakRule weakRule = new WeakRule();

    @Test
    public void weakRuleTestFirstConf() {
        //Point point = new Point(1, 2);
        supervisor.setCurrentPoint(new Point(1, 2));

        board.setCell(new Point(0, 0), Colour.white);
        board.setCell(new Point(2, 1), Colour.white);
        board.setCell(new Point(3, 3), Colour.white);

        board.setCell(new Point(2, 0), Colour.black);
        board.setCell(new Point(3, 0), Colour.black);
        board.setCell(new Point(3, 1), Colour.black);

        assertTrue(weakRule.isValidPosition(supervisor));
    }

    @Test
    public void weakRuleTestSecondConf() {
        //Point point = new Point(1, 1);
        supervisor.setCurrentPoint(new Point(1, 1));

        board.setCell(new Point(3, 0), Colour.white);
        board.setCell(new Point(0, 3), Colour.white);
        board.setCell(new Point(2, 2), Colour.white);
        board.setCell(new Point(2, 3), Colour.white);

        board.setCell(new Point(2, 0), Colour.black);
        board.setCell(new Point(2, 1), Colour.black);
        board.setCell(new Point(4, 2), Colour.black);
        board.setCell(new Point(0, 2), Colour.black);

        assertFalse(weakRule.isValidPosition(supervisor));
    }

    @Test
    public void weakRuleTestThirdConf() {
        //Point point = new Point(1, 0);
        supervisor.setCurrentPoint(new Point(1, 0));

        board.setCell(new Point(0, 1), Colour.white);
        board.setCell(new Point(2, 2), Colour.white);

        board.setCell(new Point(0, 0), Colour.black);
        board.setCell(new Point(2, 1), Colour.black);

        assertFalse(weakRule.isValidPosition(supervisor));
    }

}
