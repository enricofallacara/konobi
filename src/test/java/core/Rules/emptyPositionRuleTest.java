package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class emptyPositionRuleTest {

    private final StatusSupervisor supervisor = new StatusSupervisor(11);
    private final Board board = supervisor.getBoard();
    private final Point point = new Point(1, 2);
    private final EmptyRule emptyRule = new EmptyRule();

    @Test
    public void emptyPositionRuleValid() {
        supervisor.setCurrentPoint(point);
        assertTrue(emptyRule.isValid(supervisor));
    }

    @Test
    public void emptyPositionRuleInvalid() {
        supervisor.setCurrentPoint(point);
        board.setCellColour(point, Colour.BLACK);
        assertFalse(emptyRule.isValid(supervisor));
    }

}
