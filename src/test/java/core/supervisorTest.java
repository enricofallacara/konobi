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
}
