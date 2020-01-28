package core;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class pieRuleTest {

    @Test
    public void checkQuery(){
        assertTrue(PieRule.query(2));
    }
    @Test
    public void rulebookQueryTest(){
        assertTrue(Rulebook.queryPieRule(2));
        assertFalse(Rulebook.queryPieRule(4));
    }
}
