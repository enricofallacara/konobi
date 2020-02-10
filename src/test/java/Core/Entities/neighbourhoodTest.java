package Core.Entities;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class neighbourhoodTest {

    private final Board board = new Board(11);

    @Test
    public void testMooreNeighbours() {
        Point p = new Point(3,3);
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
        Point p = new Point(3,3);
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
        Point p = new Point(3,3);
        ArrayList<Cell> weakNeighbours = Neighbourhood.getNeighbours(board, p, 1, Neighbourhood::isWeakNeighbour).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Cell> expectedNeighbours = new ArrayList<>() {{
            add(board.getCell(new Point(p.x+1,p.y+1)));
            add(board.getCell(new Point(p.x+1,p.y-1)));
            add(board.getCell(new Point(p.x-1,p.y+1)));
            add(board.getCell(new Point(p.x-1,p.y-1))); }};
        assertEquals(new HashSet<>(weakNeighbours), new HashSet<>(expectedNeighbours));
    }
}
