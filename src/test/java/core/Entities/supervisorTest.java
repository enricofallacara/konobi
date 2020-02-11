package core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class supervisorTest {

    private final StatusSupervisor supervisor = new StatusSupervisor(11);

    private void setTestBoard() {
        Board board = supervisor.getBoard();
        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);
    }

    @Test
    public void testNewMoveInvalid() {
        setTestBoard();
        assertFalse(supervisor.newMove(new Point(1, 1)));
        assertSame(Color.black, supervisor.getCurrentPlayer().getColor());
    }

    @Test
    public void testNewMoveValid() {
        setTestBoard();
        supervisor.getBoard().setCell(new Point(2, 1), Color.black);
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
