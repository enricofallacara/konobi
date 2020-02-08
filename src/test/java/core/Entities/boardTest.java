package core.Entities;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Color;
import core.Entities.Player;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class boardTest {
    private Board board = new Board(11);

    @Test
    public void testSize(){
        assertEquals(11, board.getSize());
    }

    @Test
    public void testCellCoordinates(){
        assertEquals(new Point(3,4), board.getCell(new Point(3,4)).getCoordinates());
    }

    @Test
    public void setCellTest(){
        Point p = new Point(2, 3);
        board.setCell(p, Color.black);
        assertEquals(Color.black, board.getCell(p).getColor());
    }

    @Test
    public void testSlice() {
        Cell[] actual = board.slice(2, 4, 2, 4);
        Cell[] expected = new Cell[]{board.getCell(new Point(2, 2)),
                board.getCell(new Point(2, 3)),
                board.getCell(new Point(3, 2)),
                board.getCell(new Point(3, 3))};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void testEmptySlice() {
        Cell[] actual = board.slice(0, 0, 5, 8);
        Cell[] expected = new Cell[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void testIterator(){
        Point[] list = new Point[11 * 11];
        Point[] actual = new Point[11 * 11];
        int idx = 0;
        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                list[idx] = new Point(j,i);
                idx++;
            }
        }
        idx = 0;
        for (Cell c: board){
            actual[idx] = c.getCoordinates();
            idx++;
        }
        assertArrayEquals(list, actual);
    }

    @Test
    public void testMooreNeighbours() {
        Point p = new Point(3,3);
        ArrayList<Cell> mooreNeighbours = board.getMooreNeighbours(p, 1).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
                                add(board.getCell(p));
                                add(board.getCell(new Point(p.x,p.y+1)));
                                add(board.getCell(new Point(p.x,p.y-1)));
                                add(board.getCell(new Point(p.x+1,p.y)));
                                add(board.getCell(new Point(p.x-1,p.y)));
                                add(board.getCell(new Point(p.x+1,p.y+1)));
                                add(board.getCell(new Point(p.x+1,p.y-1)));
                                add(board.getCell(new Point(p.x-1,p.y+1)));
                                add(board.getCell(new Point(p.x-1,p.y-1))); }};
            assertEquals(new HashSet<>(mooreNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testStrongNeighbours(){
        Point p = new Point(3,3);
        ArrayList<Cell> strongNeighbours = board.getNeighbours(p, 1, Board::isStrongNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
                                add(board.getCell(new Point(p.x,p.y + 1)));
                                add(board.getCell(new Point(p.x - 1,p.y)));
                                add(board.getCell(new Point(p.x + 1,p.y)));
                                add(board.getCell(new Point(p.x,p.y - 1))); }};
        assertEquals(new HashSet<>(strongNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testWeakNeighbours(){
        Point p = new Point(3,3);
        ArrayList<Cell> weakNeighbours = board.getNeighbours(p, 1, Board::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
                                add(board.getCell(new Point(p.x+1,p.y+1)));
                                add(board.getCell(new Point(p.x+1,p.y-1)));
                                add(board.getCell(new Point(p.x-1,p.y+1)));
                                add(board.getCell(new Point(p.x-1,p.y-1))); }};
        assertEquals(new HashSet<>(weakNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testOnBoard() {
        assertTrue(board.isOnBoard(new Point(0, 0)));
        assertTrue(board.isOnBoard(new Point(10, 10)));
        assertTrue(board.isOnBoard(new Point(0, 10)));
        assertTrue(board.isOnBoard(new Point(10, 0)));
    }

    @Test
    public void testOnBoardAgain() {
        assertFalse(board.isOnBoard(new Point(10,11)));
        assertFalse(board.isOnBoard(new Point(-1,0)));
    }

    @Test
    public void testIsOnEndingEdgeForBlack() {
        Point point1 = new Point(0, 10);
        Player black = new Player(Color.black);
        assertTrue(board.isOnEndingEdge(point1, black));
        Point point2 = new Point(0, 5);
        assertFalse(board.isOnEndingEdge(point2, black));
    }

    @Test
    public void testIsOnEndingEdgeForWhite() {
        Point point1 = new Point(10, 1);
        Player white = new Player(Color.white);
        assertTrue(board.isOnEndingEdge(point1, white));
        Point point2 = new Point(3, 0);
        assertFalse(board.isOnEndingEdge(point2, white));
    }

}
