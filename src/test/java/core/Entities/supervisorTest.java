package core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class supervisorTest {

    private final StatusSupervisor supervisor = new StatusSupervisor(11);

    private void setTestBoard() {
        Board board = supervisor.getBoard();
        board.setCell(new Point(0, 0), Colour.black);
        board.setCell(new Point(0, 1), Colour.white);
        board.setCell(new Point(2, 2), Colour.white);
    }

    @Test
    public void testNewMoveInvalid() {
        setTestBoard();
        assertFalse(supervisor.newMove(new Point(1, 1)));
        assertSame(Colour.black, supervisor.getCurrentPlayer().getColour());
    }

    @Test
    public void testNewMoveValid() {
        setTestBoard();
        supervisor.getBoard().setCell(new Point(2, 1), Colour.black);
        assertTrue(supervisor.newMove(new Point(2, 3)));
        assertSame(Colour.white, supervisor.getCurrentPlayer().getColour());
    }

    @Test
    public void testPerformPieRule() {
        assertEquals(Colour.black, supervisor.getCurrentPlayer().getColour());
        supervisor.newMove(new Point(0,0));
        assertEquals(Colour.white, supervisor.getCurrentPlayer().getColour());
        assertEquals("playerTwo", supervisor.getCurrentPlayer().getName());
        supervisor.performPieRule();
        assertEquals(Colour.white, supervisor.getCurrentPlayer().getColour());
        assertEquals("playerOne", supervisor.getCurrentPlayer().getName());
    }

    @Test
    public void testPerformPassRule() {
        supervisor.performPassRule();
        assertEquals(Colour.white, supervisor.getCurrentPlayer().getColour());
    }

}
