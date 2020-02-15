package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class crosscutRuleTest {
    private final StatusSupervisor supervisor = new StatusSupervisor(2);
    private final Board board = supervisor.getBoard();//new Board(2);
    private final CrosscutRule crosscutRule = new CrosscutRule();

    @Test
    public void testCrosscutRuleEmpty() {
        //Point point = new Point(1, 1);
        supervisor.setCurrentPoint(new Point(1, 1));
        assertTrue(crosscutRule.isValid(supervisor));
    }

    @Test
    public void testCrosscutRuleInvalid() {
        //Point point = new Point(0, 1);
        supervisor.setCurrentPoint(new Point(0, 1));
        board.setCell(new Point(0, 0), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(1, 0), Colour.white);
        assertTrue(crosscutRule.isValid(supervisor));
    }

    @Test
    public void testCrosscutRuleFirstConf() {
        //Point point = new Point(1, 0);
        supervisor.setCurrentPoint(new Point(1, 0));
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(0, 0), Colour.white);
        assertFalse(crosscutRule.isValid(supervisor));
    }

    @Test
    public void testCrosscutRuleInvalidSecondConf() {
        //Point point = new Point(0, 0);
        supervisor.setCurrentPoint(new Point(0, 0));
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(1, 0), Colour.black);
        assertTrue(crosscutRule.isValid(supervisor));
    }

    @Test
    public void testCrosscutRuleInvalidThirdConf() {
        //Point point = new Point(1, 1);
        supervisor.setCurrentPoint(new Point(1, 1));
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 0), Colour.black);
        board.setCell(new Point(0, 0), Colour.white);
        assertTrue(crosscutRule.isValid(supervisor));
    }

    @Test
    public void testCrosscutRuleFourthConf() {
        //Point point = new Point(0, 1);
        supervisor.setCurrentPoint(new Point(0, 1));
        board.setCell(new Point(1, 0), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(0, 0), Colour.white);
        assertFalse(crosscutRule.isValid(supervisor));
    }

}
