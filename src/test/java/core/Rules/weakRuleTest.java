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
        supervisor.setCurrentPoint(new Point(1, 2));

        board.setCellColour(new Point(0, 0), Colour.WHITE);
        board.setCellColour(new Point(2, 1), Colour.WHITE);
        board.setCellColour(new Point(3, 3), Colour.WHITE);

        board.setCellColour(new Point(2, 0), Colour.BLACK);
        board.setCellColour(new Point(3, 0), Colour.BLACK);
        board.setCellColour(new Point(3, 1), Colour.BLACK);

        assertTrue(weakRule.isValid(supervisor));
    }

    @Test
    public void weakRuleTestSecondConf() {
        supervisor.setCurrentPoint(new Point(1, 1));

        board.setCellColour(new Point(3, 0), Colour.WHITE);
        board.setCellColour(new Point(0, 3), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);
        board.setCellColour(new Point(2, 3), Colour.WHITE);

        board.setCellColour(new Point(2, 0), Colour.BLACK);
        board.setCellColour(new Point(2, 1), Colour.BLACK);
        board.setCellColour(new Point(4, 2), Colour.BLACK);
        board.setCellColour(new Point(0, 2), Colour.BLACK);

        assertFalse(weakRule.isValid(supervisor));
    }

    @Test
    public void weakRuleTestThirdConf() {
        supervisor.setCurrentPoint(new Point(1, 0));

        board.setCellColour(new Point(0, 1), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);

        board.setCellColour(new Point(0, 0), Colour.BLACK);
        board.setCellColour(new Point(2, 1), Colour.BLACK);

        assertFalse(weakRule.isValid(supervisor));
    }

}
