package user_interface;

import core.Supervisor;
import org.junit.Test;

import java.awt.*;

public class consoleTest {

    @Test
    public void displayTest(){
        Supervisor supervisor = new Supervisor(11);
        Console console = new Console();
        supervisor.newMove(new Point(5,4));
        supervisor.newMove(new Point(10,2));
        supervisor.newMove(new Point(0,5));
        supervisor.newMove(new Point(6,1));
        console.display(supervisor.getBoard());
    }
}
