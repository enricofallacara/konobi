package core;
import org.junit.Test;

import static org.junit.Assert.*;
public class boardTest {

    @Test
    public void sizeTest(){
        Board board = new Board(11);
        assertEquals(board.getSize(), 11);
    }
}
