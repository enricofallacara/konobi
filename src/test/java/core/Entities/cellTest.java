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
        cell.setColour(Colour.BLACK);
        assertSame(cell.getColour(), Colour.BLACK);
    }

    @Test
    public void testColorEquality() {
        cell.setColour(Colour.WHITE);
        assertTrue(cell.hasThisColour(Colour.WHITE));
        assertFalse(cell.hasThisColour(Colour.BLACK));
    }

}
