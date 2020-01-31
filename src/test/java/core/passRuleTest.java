package core;

import org.junit.Test;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

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
