package core;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Supervisor;
import core.Rules.PassRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class passRuleTest {
    @Test
    public void checkQuery(){
        Supervisor supervisor = new Supervisor(11);
        Board board = supervisor.getBoard();
        PassRule passRule = new PassRule();

        assertFalse(passRule.isValid(supervisor));
        for(Cell c: board){ c.setColor(supervisor.getCurrentPlayer().getColor());}
        assertTrue(passRule.isValid(supervisor));
    }
}
