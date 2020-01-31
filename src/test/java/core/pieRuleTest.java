package core;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class pieRuleTest {

    @Test
    public void checkQuery(){
        PieRule pieRule = new PieRule();
        Supervisor supervisor = new Supervisor(11);
        supervisor.newMove(new Point(0, 0));
        assertTrue(pieRule.isValid(supervisor));
    }
    /*@Test
    public void rulebookQueryTest(){
        assertTrue(Rulebook.queryPieRule(2));
        assertFalse(Rulebook.queryPieRule(4));
    }*/
}
