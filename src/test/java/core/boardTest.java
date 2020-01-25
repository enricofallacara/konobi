package core;
import org.junit.Test;

import java.awt.*;
import java.util.List;

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

    @Test
    public void iteratorTest(){
        Board board = new Board(11);
        Point[] list = new Point[11*11];

        int idx = 0;
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                list[idx] = new Point(j,i);
                idx++;
            }
        }
        idx = 0;
        for(Cell c: board){
            assertEquals(c.getCoordinates(),list[idx]);
            idx++;
        }
    }

    @Test
    public void onBoardTest(){
        Board board = new Board(11);
        assertTrue(board.isOnBoard(new Point(0,0)));
        assertTrue(board.isOnBoard(new Point(10,10)));
        assertTrue(board.isOnBoard(new Point(0,10)));
        assertTrue(board.isOnBoard(new Point(10,0)));
        assertFalse(board.isOnBoard(new Point(10,11)));
        assertFalse(board.isOnBoard(new Point(-1,0)));
    }

    @Test
    public void strongNeighboursTest(){
        Board board = new Board(11);
        Point p = new Point(3,3);

        Cell[] strongNeighbours = board.getStrongNeighbours(p);
        Cell[] expectedNeighbours = new Cell[]{board.getCell(new Point(p.x,p.y+1)),
                board.getCell(new Point(p.x,p.y-1)),
                board.getCell(new Point(p.x+1,p.y)),
                board.getCell(new Point(p.x-1,p.y))};
        assertArrayEquals(strongNeighbours,expectedNeighbours);
    }
}
