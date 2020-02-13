package core.Entities;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;


public class Board implements Iterable<Cell>{

    private final int size;
    private final Cell[][] grid;

    public Board(int s){
        size = s;
        grid = IntStream.range(0, s).mapToObj(x -> IntStream.range(0, s)
                .mapToObj(y -> new Cell(new Point(x, y))).toArray(Cell[]::new)).toArray(Cell[][]::new);
    }

    public int getSize(){ return size; }

    public Cell getCell(Point p){ return grid[p.x][p.y]; }

    public void setCell(Point p, Color c) {
        getCell(p).setColor(c);
    }

    public Cell[] slice(int startX, int endX, int startY, int endY) {
        Point p1 = new Point(startX,startY);
        Point p2 = new Point(endX-1,endY-1 );
        if(isOnBoard(p1) && isOnBoard(p2))
            return Arrays.stream(grid).skip(startY).limit(endY - startY).
                    flatMap(x -> Arrays.stream(x).skip(startX).limit(endX - startX)).toArray(Cell[]::new);
        else
            return new Cell[]{};
    }

    @Override
    public Iterator<Cell> iterator(){
        return new Iterator<>() {

            private final Point currentPoint = new Point(0, 0);

            @Override
            public boolean hasNext() {
                return currentPoint.x <= size - 1 && currentPoint.y <= size - 1;
            }

            @Override
            public Cell next() {
                Point tmp = new Point(currentPoint);
                Point newLocation = (currentPoint.x == size - 1) ? new Point(0, currentPoint.y + 1) : new Point(currentPoint.x + 1, currentPoint.y);
                currentPoint.setLocation(newLocation);
                return getCell(tmp);
            }
        };
    }

    public boolean isOnBoard(Point point){
        return (0<= point.x && point.x < size) && (0 <= point.y && point.y < size);
    }

    public boolean isOnEndingEdge(Point point, Color color) {
        return (color == Color.white) ? point.x == size - 1 : point.y == size - 1;
    }

}