package core.Rules;

import core.Entities.Board;
import core.Entities.Color;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class crosscutRuleTest {
    private Board board = new Board(2);

    @Test
    public void testCrosscutRuleEmpty() {
        Point point = new Point(1, 1);
        assertTrue(CrosscutRule.isValid(point, board, Color.black));
    }

    @Test
    public void testCrosscutRuleInvalid() {
        Point point = new Point(0, 1);
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 0), Color.white);
        assertTrue(CrosscutRule.isValid(point, board, Color.black));
    }

    @Test
    public void testCrosscutRuleFirstConf() {
        Point point = new Point(1, 0);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, Color.black));
    }

    @Test
    public void testCrosscutRuleSecondConf() {
        Point point = new Point(0, 0);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 0), Color.black);
        assertFalse(CrosscutRule.isValid(point, board, Color.white));
    }

    @Test
    public void testCrosscutRuleThirdConf() {
        Point point = new Point(1, 1);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, Color.white));
    }

    @Test
    public void testCrosscutRuleFourthConf() {
        Point point = new Point(0, 1);
        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, Color.black));
    }

}
