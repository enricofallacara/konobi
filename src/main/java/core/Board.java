package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiPredicate;
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
                return currentPoint.x <= size-1 && currentPoint.y <= size-1;
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

    /*public ArrayList<Cell> getStrongNeighbours(Point p){
        Point[] strongPoints = new Point[]{new Point(p.x,p.y+1),
                                           new Point(p.x,p.y-1),
                                           new Point(p.x+1,p.y),
                                           new Point(p.x-1,p.y)};

        return Arrays.stream(strongPoints).filter(this::isOnBoard).map(this::getCell).collect(Collectors.toCollection(ArrayList::new));
        return getNeighbours(p, Board::isStrongNeighbour);
    }

    public ArrayList<Cell> getWeakNeighbours(Point p){
        Point[] weakPoints = new Point[]{new Point(p.x+1,p.y+1),
                                           new Point(p.x+1,p.y-1),
                                           new Point(p.x-1,p.y+1),
                                           new Point(p.x-1,p.y-1)};

        return Arrays.stream(weakPoints).filter(this::isOnBoard).map(this::getCell).collect(Collectors.toCollection(ArrayList::new));
        return getNeighbours(p, Board::isWeakNeighbour);
    }*/

    public static boolean isStrongNeighbour(Point target, Point query) {
        return target.distance(query) == 1.0;
    }

    public static boolean isWeakNeighbour(Point target, Point query) {
        return target.distance(query) == Math.sqrt(2);  // se ci limitiamo a intorni a un livello, questo puo usare .negate()
    }

    /*public boolean isSameColor(Point target, Point query) {
        return getCell(target).getColor() == getCell(query).getColor();
    }*/

    /*public ArrayList<Cell> getNeighbours(Point point, Function<Point, ArrayList<Cell>> function) {
        return function.apply(point);
    }*/

    @SafeVarargs
    public final ArrayList<Cell> getNeighbours(Point point, BiPredicate<Point, Point>... functions) {
        return Arrays.stream(getMooreNeighbours(point, 1)).
                filter(cell -> Arrays.stream(functions).allMatch(x -> x.test(point, cell.getCoordinates()))).collect(Collectors.toCollection(ArrayList::new));
    }

    @SafeVarargs
    public final ArrayList<Cell> getColoredNeighbours(Point point, Player player, BiPredicate<Point, Point>... functions) {
        ArrayList<Cell> neighbours = getNeighbours(point, functions);
        neighbours.removeIf(x -> x.getColor() != player.getColor());
        return neighbours;
    }

    public boolean isOnBoard(Point p){ return (0<= p.x && p.x < size) && (0 <= p.y && p.y < size);}
}
