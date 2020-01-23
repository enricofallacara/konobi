package core;

import org.junit.Test;
import static org.junit.Assert.*;


public class cellTest {
    @Test
    public void testVariables() {
       Cell cell = new Cell();
       assertEquals(cell.getColor(), cellStatus.empty);
    }
}
