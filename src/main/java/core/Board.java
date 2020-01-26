package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Board implements Iterable<Cell>{

    public Board(int s){
        size = s;  // Control s > 0.
        grid = IntStream.range(0, s).mapToObj(x -> IntStream.range(0, s)
                .mapToObj(y -> new Cell(new Point(x, y))).toArray(Cell[]::new)).toArray(Cell[][]::new);
    }

    private int size;
    private Cell[][] grid;

    public int getSize(){ return size;}

    public Cell getCell(Point p){ return grid[p.x][p.y];}
    public void setCell(Point p, Color c) {
        getCell(p).setColor(c);
    }

    public ArrayList<Cell> getColumn(int column) {
        // Control column is legal.
        return IntStream.range(0, size - 1).mapToObj(x -> grid[x][column])
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getRow(int row) {
        // Control row is legal.
        return Arrays.stream(grid[row], 0, size - 1)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterator<Cell> iterator(){
        return new Iterator<Cell>() {

            private Point currentPoint = new Point(0, 0);

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

    public ArrayList<Cell> getStrongNeighbours(Point p){
        Point[] strongPoints = new Point[]{new Point(p.x, p.y+1),
                                           new Point(p.x, p.y-1),
                                           new Point(p.x+1, p.y),
                                           new Point(p.x-1, p.y)};

        return Arrays.stream(strongPoints).filter(this::isOnBoard).map(this::getCell)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getWeakNeighbours(Point p){
        Point[] weakPoints = new Point[]{new Point(p.x+1, p.y+1),
                                         new Point(p.x+1, p.y-1),
                                         new Point(p.x-1, p.y+1),
                                         new Point(p.x-1, p.y-1)};

        return Arrays.stream(weakPoints).filter(this::isOnBoard).map(this::getCell)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getNeighbours(Point point, Function<Point, ArrayList<Cell>> function) {
        return function.apply(point);
    }

    public ArrayList<Cell> getColoredNeighbours(Point point, Player player, Function<Point, ArrayList<Cell>> function) {
        ArrayList<Cell> neighbours = getNeighbours(point, function);
        neighbours.removeIf(x -> x.getColor() != player.getColor());
        return neighbours;
    }

    public boolean isOnBoard(Point p) {
        return (0 <= p.x && p.x < size) && (0 <= p.y && p.y < size);
    }
}
