package core.Entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class playerTest {

    @Test
    public void testColorBlack() {
        Player player = new Player(Colour.black);
        assertEquals(player.getColour(), Colour.black);
    }

    @Test
    public void testColorWhite() {
        Player player = new Player(Colour.white);
        assertEquals(player.getColour(), Colour.white);
    }

    @Test
    public void testSwitchSidesFromWhiteToBlack() {
        Player player = new Player(Colour.white);
        player.changeSide();
        assertEquals(player.getColour(), Colour.black);
    }

    @Test
    public void testSwitchSidesFromBlackToWhite() {
        Player player = new Player(Colour.black);
        player.changeSide();
        assertEquals(player.getColour(), Colour.white);
    }

}
