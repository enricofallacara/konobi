package core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import java.awt.Point;


public class cellTest {
    @Test
    public void testVariables() {
       Cell cell = new Cell(new Point(3, 4));
       assertEquals(cell.getColor(), cellStatus.empty);
       assertEquals(cell.getCoordinates(), new Point(3, 4));
    }
}
