package core;

import core.Entities.Color;
import core.Entities.Supervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class supervisorTest {
    private Supervisor supervisor = new Supervisor(11);

    @Test
    public void testNewMoveInvalid() {
        assertFalse(supervisor.newMove(new Point(12, 3)));
        assertSame(supervisor.getCurrentPlayer().getColor(), Color.black);
    }

    @Test
    public void testNewMoveValid() {
        assertTrue(supervisor.newMove(new Point(2, 3)));
        assertSame(supervisor.getCurrentPlayer().getColor(), Color.white);
    }

    @Test
    public void testPerformPieRule() {
        assertEquals(supervisor.getCurrentPlayer().getColor(), Color.black);
        supervisor.newMove(new Point(0,0));
        assertEquals(supervisor.getCurrentPlayer().getColor(), Color.white);
        assertEquals(supervisor.getCurrentPlayer().getName(), "playerTwo");
        supervisor.performPieRule();
        assertEquals(supervisor.getCurrentPlayer().getColor(), Color.white);
        assertEquals(supervisor.getCurrentPlayer().getName(), "playerOne");
    }

    @Test
    public void testPerformPassRule() {
        supervisor.performPassRule();
        assertEquals(supervisor.getCurrentPlayer().getColor(), Color.white);
    }

}
