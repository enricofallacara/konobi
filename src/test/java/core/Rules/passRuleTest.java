package core.Rules;

import core.Entities.Cell;
import core.Entities.StatusSupervisor;
import org.junit.Test;

import static org.junit.Assert.*;

public class passRuleTest {
    private final StatusSupervisor supervisor = new StatusSupervisor(11);
    private final PassRule passRule = new PassRule();

    @Test
    public void testPassRuleEmptyBoard(){
        assertFalse(passRule.isValid(supervisor));
    }

    @Test
    public void testPassRuleFullBoard() {
        for (Cell c: supervisor.getBoard()) { c.setColour(supervisor.getCurrentPlayer().getColour()); }
        assertTrue(passRule.isValid(supervisor));
    }

}
