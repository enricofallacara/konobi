package core;

import core.Entities.Board;
import core.Entities.Color;
import core.Entities.Player;
import core.Rules.CrosscutRule;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testCrosscutRule {
    private Board board = new Board(2);

    @Test
    public void testCrosscutRuleEmpty() {
        Point point = new Point(1, 1);
        Player player = new Player(Color.black);
        assertTrue(CrosscutRule.isValid(point, board, player));
    }

    @Test
    public void testCrosscutRuleInvalid() {
        Player player = new Player(Color.black);
        Point point = new Point(0, 1);
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 0), Color.white);
        assertTrue(CrosscutRule.isValid(point, board, player));
    }

    @Test
    public void testCrosscutRuleFirstConf() {
        Player player = new Player(Color.black);
        Point point = new Point(1, 0);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, player));
    }

    @Test
    public void testCrosscutRuleSecondConf() {
        Player player = new Player(Color.white);
        Point point = new Point(0, 0);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(1, 0), Color.black);
        assertFalse(CrosscutRule.isValid(point, board, player));
    }

    @Test
    public void testCrosscutRuleThirdConf() {
        Player player = new Player(Color.white);
        Point point = new Point(1, 1);
        board.setCell(new Point(0, 1), Color.black);
        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, player));
    }

    @Test
    public void testCrosscutRuleFourthConf() {
        Player player = new Player(Color.black);
        Point point = new Point(0, 1);
        board.setCell(new Point(1, 0), Color.black);
        board.setCell(new Point(1, 1), Color.white);
        board.setCell(new Point(0, 0), Color.white);
        assertFalse(CrosscutRule.isValid(point, board, player));
    }

}
