package core;

import core.Entities.Board;
import core.Entities.Color;
import core.Rules.EmptyRule;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class emptyPositionRuleTest {
    private Board board = new Board(11);
    private Point point = new Point(1, 2);

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
