package core;

import org.junit.Test;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class passRuleTest {
    @Test
    public void checkQuery(){
        Board board = new Board(11);
        Player player = new Player(Color.black);

        assertFalse(PassRule.query(board,player));
        for(Cell c: board){ c.setColor(player.getColor());}
        assertTrue(PassRule.query(board,player));
    }
}
