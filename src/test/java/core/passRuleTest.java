package core;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Supervisor;
import core.Rules.PassRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class passRuleTest {
    private Supervisor supervisor = new Supervisor(11);
    private PassRule passRule = new PassRule();

    @Test
    public void testPassRuleEmptyBoard(){
        assertFalse(passRule.isValid(supervisor));
    }

    @Test
    public void testPassRuleFullBoard() {
        for (Cell c: supervisor.getBoard()) { c.setColor(supervisor.getCurrentPlayer().getColor()); }
        assertTrue(passRule.isValid(supervisor));
    }

}
