package core;

import org.junit.Test;
import static org.junit.Assert.*;

public class playerTest {

    @Test
    public void testColor() {
        Player player = new Player(Color.black);
        assertEquals(player.getColor(), Color.black);
    }

    @Test
    public void testSwitchSides() {
        Player player = new Player(Color.white);
        player.changeSide();
        assertEquals(player.getColor(), Color.black);
    }


}
