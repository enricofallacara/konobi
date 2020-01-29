package core;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class supervisorTest {

    @Test
    public void newMoveTest() {
        Supervisor supervisor = new Supervisor(11);

        assertFalse(supervisor.newMove(new Point(12, 3)));
        assertSame(supervisor.getCurrentPlayer().getColor(), Color.black);
        assertTrue(supervisor.newMove(new Point(2, 3)));
        assertSame(supervisor.getCurrentPlayer().getColor(), Color.white);
    }

    @Test
    public void performPieRuleTest(){

        Supervisor supervisor = new Supervisor(11);
        assertEquals(supervisor.getCurrentPlayer().getColor(),Color.black);
        supervisor.newMove(new Point(0,0));
        assertEquals(supervisor.getCurrentPlayer().getColor(),Color.white);
        assertEquals(supervisor.getCurrentPlayer().getName(), "playerTwo");
        supervisor.performPieRule();
        assertEquals(supervisor.getCurrentPlayer().getColor(),Color.white);
        assertEquals(supervisor.getCurrentPlayer().getName(), "playerOne");
    }
}
