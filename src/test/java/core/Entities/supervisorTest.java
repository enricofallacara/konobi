package core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class supervisorTest {

    private final StatusSupervisor supervisor = new StatusSupervisor(11);

    private void setTestBoard() {
        Board board = supervisor.getBoard();
        board.setCellColour(new Point(0, 0), Colour.BLACK);
        board.setCellColour(new Point(0, 1), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);
    }

    @Test
    public void testNewMoveInvalid() {
        setTestBoard();
        assertFalse(supervisor.newMove(new Point(1, 1)));
        assertSame(Colour.BLACK, supervisor.getCurrentPlayer().getColour());
    }

    @Test
    public void testNewMoveValid() {
        setTestBoard();
        supervisor.getBoard().setCellColour(new Point(2, 1), Colour.BLACK);
        assertTrue(supervisor.newMove(new Point(2, 3)));
        assertSame(Colour.WHITE, supervisor.getCurrentPlayer().getColour());
    }

    @Test
    public void testPerformPieRule() {
        assertEquals(Colour.BLACK, supervisor.getCurrentPlayer().getColour());
        supervisor.newMove(new Point(0,0));
        assertEquals(Colour.WHITE, supervisor.getCurrentPlayer().getColour());
        assertEquals("playerTwo", supervisor.getCurrentPlayer().getName());
        supervisor.performPieRule();
        assertEquals(Colour.WHITE, supervisor.getCurrentPlayer().getColour());
        assertEquals("playerOne", supervisor.getCurrentPlayer().getName());
    }

    @Test
    public void testPerformPassRule() {
        supervisor.performPassRule();
        assertEquals(Colour.WHITE, supervisor.getCurrentPlayer().getColour());
    }

}
