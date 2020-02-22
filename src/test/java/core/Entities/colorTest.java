package core.Entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class colorTest {

    @Test
    public void testOppositeColorOfWhite() {
        assertEquals(Colour.WHITE.getOppositeColor(), Colour.BLACK);
    }

    @Test
    public void testOppositeColorOfBlack() {
        assertEquals(Colour.BLACK.getOppositeColor(), Colour.WHITE);
    }

}
