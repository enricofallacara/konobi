package Core.Entities;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class neighbourhoodTest {

    private final Board board = new Board(11);
    private final Point p = new Point(3, 3);

    @Test
    public void testMooreNeighbours() {
        ArrayList<Cell> mooreNeighbours = Neighbourhood.getMooreNeighbours(board, p, 1).collect(Collectors.toCollection(ArrayList::new));
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
        ArrayList<Cell> strongNeighbours = Neighbourhood.getNeighbours(board, p, 1, Neighbourhood::isStrongNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
            add(board.getCell(new Point(p.x,p.y + 1)));
            add(board.getCell(new Point(p.x - 1,p.y)));
            add(board.getCell(new Point(p.x + 1,p.y)));
            add(board.getCell(new Point(p.x,p.y - 1))); }};
        assertEquals(new HashSet<>(strongNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testWeakNeighbours(){
        ArrayList<Cell> weakNeighbours = Neighbourhood.getNeighbours(board, p, 1, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
            add(board.getCell(new Point(p.x+1,p.y+1)));
            add(board.getCell(new Point(p.x+1,p.y-1)));
            add(board.getCell(new Point(p.x-1,p.y+1)));
            add(board.getCell(new Point(p.x-1,p.y-1))); }};
        assertEquals(new HashSet<>(weakNeighbours), new HashSet<>(expectedNeighbours));
    }

    @Test
    public void testColoredNeighboursNone() {
        ArrayList<Cell> coloredNeighbours = Neighbourhood.getColoredNeighbours(board, p, 1, Color.black, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        assertTrue(coloredNeighbours.isEmpty());
    }

    @Test
    public void testColoredNeighboursSome() {
        board.setCell(new Point(p.x + 1, p.y), Color.black);
        board.setCell(new Point(p.x + 1, p.y + 1), Color.white);
        board.setCell(new Point(p.x - 1, p.y), Color.white);
        board.setCell(new Point(p.x + 1, p.y - 1), Color.black);
        ArrayList<Cell> coloredNeighbours = Neighbourhood.getColoredNeighbours(board, p, 1, Color.black, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{ add(board.getCell(new Point(p.x+1,p.y-1))); }};
        assertEquals(expectedNeighbours, coloredNeighbours);
    }

}