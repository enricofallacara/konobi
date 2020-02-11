package Core.Rules;

import Core.Entities.Cell;
import Core.Entities.StatusSupervisor;
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
        for (Cell c: supervisor.getBoard()) { c.setColor(supervisor.getCurrentPlayer().getColor()); }
        assertTrue(passRule.isValid(supervisor));
    }

}
