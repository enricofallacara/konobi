package core;

import core.Entities.Color;
import core.Entities.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class playerTest {

    @Test
    public void testColorBlack() {
        Player player = new Player(Color.black);
        assertEquals(player.getColor(), Color.black);
    }

    @Test
    public void testColorWhite() {
        Player player = new Player(Color.white);
        assertEquals(player.getColor(), Color.white);
    }

    @Test
    public void testSwitchSidesFromWhiteToBlack() {
        Player player = new Player(Color.white);
        player.changeSide();
        assertEquals(player.getColor(), Color.black);
    }

    @Test
    public void testSwitchSidesFromBlackToWhite() {
        Player player = new Player(Color.black);
        player.changeSide();
        assertEquals(player.getColor(), Color.white);
    }

}
