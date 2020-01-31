package core;

import org.junit.Test;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class passRuleTest {
    @Test
    public void checkQuery(){
        Supervisor supervisor = new Supervisor(11);
        supervisor.newMove(new Point(0, 0));
        //Board board = supervisor.getBoard();
        //PassRule passRule = new PassRule();
        //Player player = new Player(Color.black);

        assertFalse(Rulebook.queryPassRule(supervisor));
        for(Cell c: supervisor.getBoard()){ c.setColor(Color.black);}
        //assertTrue(Rulebook.queryPassRule(supervisor));
    }
}
