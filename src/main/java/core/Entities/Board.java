package core.Entities;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board implements Iterable<Cell>{

    public Board(int s){
        size = s;
        grid = IntStream.range(0, s).mapToObj(x -> IntStream.range(0, s)
                .mapToObj(y -> new Cell(new Point(x, y))).toArray(Cell[]::new)).toArray(Cell[][]::new);
    }
    private final int size;
    private final Cell[][] grid;

    public int getSize(){ return size; }
    public Cell getCell(Point p){ return grid[p.x][p.y]; }
    public void setCell(Point p, Color c) {
        getCell(p).setColor(c);
    }

    public Cell[] slice(int startX, int endX, int startY, int endY) {
        return Arrays.stream(grid).skip(startY).limit(endY - startY).
                flatMap(x -> Arrays.stream(x).skip(startX).limit(endX - startX)).toArray(Cell[]::new);
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

    public Stream<Cell> getMooreNeighbours(Point p, int level) {
        return Arrays.stream(
                slice(  Math.max(0, p.y - level),
                        Math.min(p.y + level + 1, size),
                        Math.max(0, p.x - level),
                        Math.min(p.x + level + 1, size))
                );
    }

    public static boolean isStrongNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 1.0; }

    public static boolean isWeakNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 2.0; }

    @SafeVarargs
    public final Stream<Cell> getNeighbours(Point point, int level, BiPredicate<Point, Point>... functions) {
        return getMooreNeighbours(point, level).filter(cell -> Arrays.stream(functions).allMatch(x -> x.test(point, cell.getCoordinates())));
    }

    @SafeVarargs
    public final Stream<Cell> getColoredNeighbours(Point point, int level, Color color, BiPredicate<Point, Point>... functions) {
        return getNeighbours(point, level, functions).filter(x -> x.hasThisColor(color));
    }

    public boolean isOnBoard(Point point){
        return (0<= point.x && point.x < size) && (0 <= point.y && point.y < size);
    }

    public boolean isOnEndingEdge(Point point, Color color) {
        return (color == Color.white) ? point.x == size - 1 : point.y == size - 1;
    }

    private static double manhattanDistance(int x1, int x2, int y1, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2); }
}