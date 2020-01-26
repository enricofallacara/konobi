package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
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
    public void setCell(Point p, Color c) {
        getCell(p).setColor(c);
    }

    public Cell[] slice(int startX, int endX, int startY, int endY) {
        return Arrays.stream(grid).skip(startY).limit(endY - startY).
                flatMap(x -> Arrays.stream(x).skip(startX).limit(endX - startX)).toArray(Cell[]::new);
    }

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

    public Cell[] getMooreNeighbours(Point p, int level) {
        return slice(Math.max(0, p.x - level), Math.min(p.x + level + 1, size), Math.max(0, p.y - level), Math.min(p.y + level + 1, size));
    }

    public ArrayList<Cell> getStrongNeighbours(Point p){
        Point[] strongPoints = new Point[]{new Point(p.x,p.y+1),
                                           new Point(p.x,p.y-1),
                                           new Point(p.x+1,p.y),
                                           new Point(p.x-1,p.y)};

        return Arrays.stream(strongPoints).filter(this::isOnBoard).map(this::getCell).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getWeakNeighbours(Point p){
        Point[] weakPoints = new Point[]{new Point(p.x+1,p.y+1),
                                           new Point(p.x+1,p.y-1),
                                           new Point(p.x-1,p.y+1),
                                           new Point(p.x-1,p.y-1)};

        return Arrays.stream(weakPoints).filter(this::isOnBoard).map(this::getCell).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getNeighbours(Point point, Function<Point, ArrayList<Cell>> function) {
        return function.apply(point);
    }

    public ArrayList<Cell> getNeighbours(Point point, BiPredicate<Point, Point> function) {
        return Arrays.stream(getMooreNeighbours(point, 1)).
                filter(cell -> function.test(point, cell.getCoordinates())).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cell> getColoredNeighbours(Point point, Player player, Function<Point, ArrayList<Cell>> function) {
        ArrayList<Cell> neighbours = getNeighbours(point, function);
        neighbours.removeIf(x -> x.getColor() != player.getColor());
        return neighbours;
    }

    public boolean isOnBoard(Point p){ return (0<= p.x && p.x < size) && (0 <= p.y && p.y < size);}
}
