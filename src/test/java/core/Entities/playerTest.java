package core.Entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class playerTest {

    @Test
    public void testColorBlack() {
        Player player = new Player(Colour.BLACK);
        assertEquals(player.getColour(), Colour.BLACK);
    }

    @Test
    public void testColorWhite() {
        Player player = new Player(Colour.WHITE);
        assertEquals(player.getColour(), Colour.WHITE);
    }

    @Test
    public void testSwitchSidesFromWhiteToBlack() {
        Player player = new Player(Colour.WHITE);
        player.changeSide();
        assertEquals(player.getColour(), Colour.BLACK);
    }

    @Test
    public void testSwitchSidesFromBlackToWhite() {
        Player player = new Player(Colour.BLACK);
        player.changeSide();
        assertEquals(player.getColour(), Colour.WHITE);
    }

}
