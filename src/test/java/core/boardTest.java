package core;
import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Color;
import core.Entities.Player;
import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class boardTest {

    @Test
    public void sizeTest(){
        Board board = new Board(11);
        assertEquals(11, board.getSize());
    }

    @Test
    public void cellCoordinatesTest(){
        Board board = new Board(11);
        assertEquals(new Point(3,4), board.getCell(new Point(3,4)).getCoordinates());
    }

    @Test
    public void setCellTest(){
        Board board = new Board(11);
        Point p = new Point(2, 3);
        board.setCell(p, Color.black);
        assertEquals(Color.black, board.getCell(p).getColor());
    }

    @Test
    public void testSlice() {
        Board board = new Board(11);
        List<Cell> portion = Arrays.asList(board.slice(2, 4, 2, 4));
        List<Cell> expected = Arrays.asList(board.getCell(new Point(2, 2)),
                board.getCell(new Point(3, 2)),
                board.getCell(new Point(2, 3)),
                board.getCell(new Point(3, 3)));
        assertTrue(expected.size() == portion.size() &&
                expected.containsAll(portion) && portion.containsAll(expected));
    }

    @Test
    public void iteratorTest(){
        Board board = new Board(11);
        Point[] list = new Point[11 * 11];
        Point[] actual = new Point[11 * 11];
        int idx = 0;
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                list[idx] = new Point(j,i);
                idx++;
            }
        }
        idx = 0;
        for(Cell c: board){
            actual[idx] = c.getCoordinates();
            idx++;
        }
        assertArrayEquals(list, actual);
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

        ArrayList<Cell> strongNeighbours = board.getNeighbours(p, 1, Board::isStrongNeighbour);
        ArrayList<Cell> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(board.getCell(new Point(p.x,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x,p.y-1)));
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y)));

        assertTrue(expectedNeighbours.size() == strongNeighbours.size() &&
                expectedNeighbours.containsAll(strongNeighbours) && strongNeighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void weakNeighboursTest(){
        Board board = new Board(11);
        Point p = new Point(3,3);

        ArrayList<Cell> weakNeighbours = board.getNeighbours(p, 1, Board::isWeakNeighbour);
        ArrayList<Cell> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y-1)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y-1)));

        assertTrue(expectedNeighbours.size() == weakNeighbours.size() &&
                expectedNeighbours.containsAll(weakNeighbours) && weakNeighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void testMooreNeighbours() {
        Board board = new Board(11);
        Point p = new Point(3,3);
        ArrayList<Cell> mooreNeighbours = board.getMooreNeighbours(p, 1);
        ArrayList<Cell> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(board.getCell(new Point(p.x,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x,p.y-1)));
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y)));
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x+1,p.y-1)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y+1)));
        expectedNeighbours.add(board.getCell(new Point(p.x-1,p.y-1)));
        assertTrue(expectedNeighbours.size() == mooreNeighbours.size() - 1 &&
                mooreNeighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void isOnEndingEdgeTest() {
        Board board = new Board(11);
        Point point = new Point(0,10);

        Player black = new Player(Color.black);
        assertTrue(board.isOnEndingEdge(point, black));

        Player white = new Player(Color.white);
        assertFalse(board.isOnEndingEdge(point, white));
    }

}
