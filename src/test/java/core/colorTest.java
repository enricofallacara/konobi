package core;

import core.Entities.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class colorTest {

    @Test
    public void testOppositeColor() {
        assertEquals(Color.white.getOppositeColor(), Color.black);
        assertEquals(Color.black.getOppositeColor(), Color.white);
    }
}
