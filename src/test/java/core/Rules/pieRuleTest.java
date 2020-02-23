package core.Rules;

import core.Entities.StatusSupervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class pieRuleTest {

    private final PieRule pieRule = new PieRule();
    private final StatusSupervisor supervisor = new StatusSupervisor(11);

    @Test
    public void checkPieRuleIsTrueOnSecondTurn(){
        supervisor.newMove(new Point(0, 0));
        assertTrue(pieRule.isValid(supervisor));
    }

    @Test
    public void checkPieRuleIsFalseOtherwise() {
        assertFalse(pieRule.isValid(supervisor));
    }

}
