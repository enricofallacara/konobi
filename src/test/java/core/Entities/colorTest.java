package core.Entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class colorTest {

    @Test
    public void testOppositeColorOfWhite() {
        assertEquals(Colour.white.getOppositeColor(), Colour.black);
    }

    @Test
    public void testOppositeColorOfBlack() {
        assertEquals(Colour.black.getOppositeColor(), Colour.white);
    }

}
