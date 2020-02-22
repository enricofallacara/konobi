package core.Entities;

import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class boardTest {

    private final Board board = new Board(11);

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
        board.setCellColour(p, Colour.BLACK);
        assertSame(Colour.BLACK, board.getCell(p).getColour());
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
    public void testNegativeSlice() {
        Cell[] actual = board.slice(3, 4, 0, -2);
        Cell[] expected = new Cell[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void testIterator(){
        Point[] expected = new Point[11 * 11];
        Point[] actual = new Point[11 * 11];
        int idx = 0;
        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                expected[idx] = new Point(j,i);
                idx++;
            }
        }
        idx = 0;
        for (Cell c: board){
            actual[idx] = c.getCoordinates();
            idx++;
        }
        assertArrayEquals(expected, actual);
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
        assertTrue(board.isOnEndingEdgeForColour(point1, Colour.BLACK));
        Point point2 = new Point(0, 5);
        assertFalse(board.isOnEndingEdgeForColour(point2, Colour.BLACK));
    }

    @Test
    public void testIsOnEndingEdgeForWhite() {
        Point point1 = new Point(10, 1);
        assertTrue(board.isOnEndingEdgeForColour(point1, Colour.WHITE));
        Point point2 = new Point(3, 0);
        assertFalse(board.isOnEndingEdgeForColour(point2, Colour.WHITE));
    }

}
