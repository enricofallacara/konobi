package core;

import core.Entities.Color;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class colorTest {

    @Test
    public void testOppositeColorOfWhite() {
        assertEquals(Color.white.getOppositeColor(), Color.black);
    }

    @Test
    public void testOppositeColorOfBlack() {
        assertEquals(Color.black.getOppositeColor(), Color.white);
    }

}
