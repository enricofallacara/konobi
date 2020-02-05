package core;

import core.Rules.PieRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class pieRuleTest {

    @Test
    public void checkQuery(){
        assertTrue(PieRule.isValid(2));
    }
}
