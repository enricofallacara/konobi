package core.Rules;

import core.Rules.PieRule;
import org.junit.Test;
import static org.junit.Assert.*;

public class pieRuleTest {

    @Test
    public void checkPieRuleIsTrueOnSecondTurn(){
        assertTrue(PieRule.isValid(2));
    }

    @Test
    public void checkPieRuleIsFalseOtherwise() {
        assertFalse(PieRule.isValid(1));
    }

}
