package core.Entities;

import core.Entities.Cell;
import core.Entities.Color;
import core.Entities.Player;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class cellTest {
    private Cell cell = new Cell(new Point(3, 4));

    @Test
    public void testCoordinates() {
       assertEquals(cell.getCoordinates(), new Point(3, 4));
    }

    @Test
    public void testNullColour() {
        assertNull(cell.getColor());
    }

    @Test
    public void testChangeColour() {
        cell.setColor(Color.black);
        assertEquals(cell.getColor(), Color.black);
    }

    @Test
    public void testColorEquality() {
        cell.setColor(Color.white);
        assertTrue(cell.hasThisColor(Color.white));
    }

    @Test
    public void testColorEqualityWithPlayer() {
        Player player = new Player(Color.white);
        cell.setColor(Color.black);
        assertFalse(cell.hasSameColorAsPlayer(player));
    }

}
