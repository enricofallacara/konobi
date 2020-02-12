package core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class cellTest {

    private final Cell cell = new Cell(new Point(3, 4));

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
        assertSame(cell.getColor(), Color.black);
    }

    @Test
    public void testColorEquality() {
        cell.setColor(Color.white);
        assertTrue(cell.hasThisColor(Color.white));
        assertFalse(cell.hasThisColor(Color.black));
    }

}
