package Core.Rules;

import Core.Entities.Board;
import Core.Entities.Color;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class emptyPositionRuleTest {
    private final Board board = new Board(11);
    private final Point point = new Point(1, 2);

    @Test
    public void emptyPositionRuleValid() {
        assertTrue(EmptyRule.isValid(point, board));
    }

    @Test
    public void emptyPositionRuleInvalid() {
        board.setCell(new Point(1, 2), Color.black);
        assertFalse(EmptyRule.isValid(point, board));
    }

}
