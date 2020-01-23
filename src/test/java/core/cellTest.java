package core;

import org.junit.Test;

import static org.junit.Assert.*;
import java.awt.Point;


public class cellTest {
    @Test
    public void testVariables() {
       Cell cell = new Cell(new Point(3, 4));
       assertEquals(cell.getColor(), cellStatus.empty);
       assertEquals(cell.getCoordinates(), new Point(3, 4));
    }

    @Test
    public void testChangeColour() {
        Cell cell = new Cell(new Point (3, 4));
        cell.setColor(cellStatus.black);
        assertEquals(cell.getColor(), cellStatus.black);
    }
}
