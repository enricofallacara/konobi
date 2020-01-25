package core;

import java.awt.Point;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Board implements Iterable<Cell>{
    public Board(int s){
        size = s;  // necessary to add a control on value of s > 0
        grid = IntStream.range(0, s).mapToObj(x -> IntStream.range(0, s)
                .mapToObj(y -> new Cell(new Point(x, y))).toArray(Cell[]::new)).toArray(Cell[][]::new);
    }
    private int size;
    private Cell[][] grid;

    public int getSize(){ return size;}
    public Cell getCell(Point p){ return grid[p.x][p.y];}

    @Override
    public Iterator<Cell> iterator(){
        return new Iterator<Cell>() {

            private Point currentPoint = new Point(0,0);

            @Override
            public boolean hasNext() {
                return currentPoint.x < size-1 || currentPoint.y < size-1;
            }

            @Override
            public Cell next() {
                Point tmp = new Point(currentPoint);
                if(currentPoint.x == size - 1){
                    currentPoint.setLocation(0 , currentPoint.y+1);
                }
                else {
                    currentPoint.setLocation(currentPoint.x+1, currentPoint.y);
                }
                return getCell(tmp);
            }
        };
    }

    public static Cell[] getStrongNeighbours(Point p){
        return new Cell[]{};
    }
    public boolean isOnBoard(Point p){ return (0<= p.x && p.x < size) && (0 <= p.y && p.y < size);}
}
