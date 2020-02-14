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
        assertNull(cell.getColour());
    }

    @Test
    public void testChangeColour() {
        cell.setColour(Colour.black);
        assertSame(cell.getColour(), Colour.black);
    }

    @Test
    public void testColorEquality() {
        cell.setColour(Colour.white);
        assertTrue(cell.hasThisColor(Colour.white));
        assertFalse(cell.hasThisColor(Colour.black));
    }

}
