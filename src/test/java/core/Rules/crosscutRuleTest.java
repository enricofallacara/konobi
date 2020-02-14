package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class crosscutRuleTest {
    private final Board board = new Board(2);

    @Test
    public void testCrosscutRuleEmpty() {
        Point point = new Point(1, 1);
        assertTrue(CrosscutRule.isValid(point, board, Colour.black));
    }

    @Test
    public void testCrosscutRuleInvalid() {
        Point point = new Point(0, 1);
        board.setCell(new Point(0, 0), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(1, 0), Colour.white);
        assertTrue(CrosscutRule.isValid(point, board, Colour.black));
    }

    @Test
    public void testCrosscutRuleFirstConf() {
        Point point = new Point(1, 0);
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(0, 0), Colour.white);
        assertFalse(CrosscutRule.isValid(point, board, Colour.black));
    }

    @Test
    public void testCrosscutRuleSecondConf() {
        Point point = new Point(0, 0);
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(1, 0), Colour.black);
        assertFalse(CrosscutRule.isValid(point, board, Colour.white));
    }

    @Test
    public void testCrosscutRuleThirdConf() {
        Point point = new Point(1, 1);
        board.setCell(new Point(0, 1), Colour.black);
        board.setCell(new Point(1, 0), Colour.black);
        board.setCell(new Point(0, 0), Colour.white);
        assertFalse(CrosscutRule.isValid(point, board, Colour.white));
    }

    @Test
    public void testCrosscutRuleFourthConf() {
        Point point = new Point(0, 1);
        board.setCell(new Point(1, 0), Colour.black);
        board.setCell(new Point(1, 1), Colour.white);
        board.setCell(new Point(0, 0), Colour.white);
        assertFalse(CrosscutRule.isValid(point, board, Colour.black));
    }

}
