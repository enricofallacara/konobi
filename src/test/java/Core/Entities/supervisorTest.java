package Core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class supervisorTest {
    private final StatusSupervisor supervisor = new StatusSupervisor(11);

    /*
    @Test
    public void testNewMoveInvalid() {
        assertFalse(supervisor.newMove(new Point(12, 3)));
        assertSame(Color.black, supervisor.getCurrentPlayer().getColor());
    }
     */

    @Test
    public void testNewMoveValid() {
        assertTrue(supervisor.newMove(new Point(2, 3)));
        assertSame(Color.white, supervisor.getCurrentPlayer().getColor());
    }

    @Test
    public void testPerformPieRule() {
        assertEquals(Color.black, supervisor.getCurrentPlayer().getColor());
        supervisor.newMove(new Point(0,0));
        assertEquals(Color.white, supervisor.getCurrentPlayer().getColor());
        assertEquals("playerTwo", supervisor.getCurrentPlayer().getName());
        supervisor.performPieRule();
        assertEquals(Color.white, supervisor.getCurrentPlayer().getColor());
        assertEquals("playerOne", supervisor.getCurrentPlayer().getName());
    }

    @Test
    public void testPerformPassRule() {
        supervisor.performPassRule();
        assertEquals(Color.white, supervisor.getCurrentPlayer().getColor());
    }

}
