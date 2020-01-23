package core;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class boardTest {

    @Test
    public void sizeTest(){
        Board board = new Board(11);
        assertEquals(board.getSize(), 11);
    }

    @Test
    public void cellCoordinatesTest(){
        Board board = new Board(11);
        assertEquals(board.getCell(new Point(3,4)).getCoordinates(), new Point(3,4));
    }
}
