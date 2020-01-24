package core;

import org.junit.Test;
import static org.junit.Assert.*;

public class playerTest {

    @Test
    public void testColor() {
        Player player = new Player(cellStatus.black);
        assertEquals(player.getColor(), cellStatus.black);
    }
}
