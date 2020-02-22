package core.Entities;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class neighbourhoodTest {

    private final Board board = new Board(11);
    private final Point p = new Point(3, 3);

    @Test
    public void testMooreNeighbours() {
        ArrayList<Cell> mooreNeighbours = Neighbourhood.getMooreNeighbours(board, p).collect(Collectors.toCollection(ArrayList::new));
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
    public void testIsStrongNeighbour() {
        Point otherP = new Point(4, 3);
        assertTrue(Neighbourhood.isStrongNeighbour(p, otherP));
        assertTrue(Neighbourhood.isStrongNeighbour(otherP, p));
    }

    @Test
    public void testIsNotStrongNeighbour() {
        Point otherP = new Point(2, 2);
        assertFalse(Neighbourhood.isStrongNeighbour(p, otherP));
        assertFalse(Neighbourhood.isStrongNeighbour(otherP, p));
    }

    @Test
    public void testIsWeakNeighbour() {
        Point otherP = new Point(2, 2);
        assertTrue(Neighbourhood.isWeakNeighbour(p, otherP));
        assertTrue(Neighbourhood.isWeakNeighbour(otherP, p));
    }

    @Test
    public void testIsNotWeakNeighbour() {
        Point otherP = new Point(4, 3);
        assertFalse(Neighbourhood.isWeakNeighbour(p, otherP));
        assertFalse(Neighbourhood.isWeakNeighbour(otherP, p));
    }

    @Test
    public void testStrongNeighbours(){
        ArrayList<Cell> strongNeighbours = Neighbourhood.getNeighboursByPosition(board, p, Neighbourhood::isStrongNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
            add(board.getCell(new Point(p.x,p.y + 1)));
            add(board.getCell(new Point(p.x - 1,p.y)));
            add(board.getCell(new Point(p.x + 1,p.y)));
            add(board.getCell(new Point(p.x,p.y - 1))); }};
        assertEquals(new HashSet<>(strongNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testWeakNeighbours(){
        ArrayList<Cell> weakNeighbours = Neighbourhood.getNeighboursByPosition(board, p, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
            add(board.getCell(new Point(p.x+1,p.y+1)));
            add(board.getCell(new Point(p.x+1,p.y-1)));
            add(board.getCell(new Point(p.x-1,p.y+1)));
            add(board.getCell(new Point(p.x-1,p.y-1))); }};
        assertEquals(new HashSet<>(weakNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testColouredNeighbours() {
        board.setCellColour(new Point(p.x + 1, p.y), Colour.BLACK);
        board.setCellColour(new Point(p.x + 1, p.y + 1), Colour.WHITE);
        board.setCellColour(new Point(p.x + 1, p.y - 1), Colour.BLACK);
        ArrayList<Cell> coloredNeighbours = Neighbourhood.getNeighboursByColour(board, p, Colour.WHITE).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{ add(board.getCell(new Point(p.x + 1,p.y + 1))); }};
        assertEquals(expectedNeighbours, coloredNeighbours);
    }

    @Test
    public void testColouredAndPositionNeighboursNone() {
        ArrayList<Cell> coloredNeighbours = Neighbourhood.getNeighboursByPositionAndColour(board, p, Colour.BLACK, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        assertTrue(coloredNeighbours.isEmpty());
    }

    @Test
    public void testColouredAndPositionNeighboursSome() {
        board.setCellColour(new Point(p.x + 1, p.y), Colour.BLACK);
        board.setCellColour(new Point(p.x + 1, p.y + 1), Colour.WHITE);
        board.setCellColour(new Point(p.x - 1, p.y), Colour.WHITE);
        board.setCellColour(new Point(p.x + 1, p.y - 1), Colour.BLACK);
        ArrayList<Cell> coloredNeighbours = Neighbourhood.getNeighboursByPositionAndColour(board, p, Colour.BLACK, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{ add(board.getCell(new Point(p.x+1,p.y-1))); }};
        assertEquals(expectedNeighbours, coloredNeighbours);
    }

}