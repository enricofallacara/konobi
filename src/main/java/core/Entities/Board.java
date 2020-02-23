package core.Entities;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Board implements Iterable<Cell>{

    private final int size;
    private final Cell[][] grid;

    public Board(int s){
        size = s;
        grid = IntStream.range(0, s).mapToObj(x -> IntStream.range(0, s)
                .mapToObj(y -> new Cell(new Point(x, y))).toArray(Cell[]::new)).toArray(Cell[][]::new);
    }

    public int getSize() { return size; }

    public Cell getCell(Point p) { return grid[p.x][p.y]; }

    public void setCellColour(Point p, Colour c) {
        getCell(p).setColour(c);
    }

    // slice uses non-inclusive end indices (e.g. slice(0, 1, 0, 1) returns only the Cell at position
    // (0, 0)), Python-style
    public Cell[] slice(int startX, int endX, int startY, int endY) {
        int[] indices = filterIndices(startX, endX, startY, endY);
        return Arrays.stream(grid).skip(indices[2]).limit(indices[3] - indices[2]).
                flatMap(x -> Arrays.stream(x).skip(indices[0]).limit(indices[1] - indices[0])).toArray(Cell[]::new);
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

    public Stream<Cell> getStartingCellsForColour(Colour colour) {
        int[] startIndexes = getStartingIndicesForColour(colour);
        return Arrays.stream(slice(startIndexes[0], startIndexes[1], startIndexes[2], startIndexes[3])).
                filter(x -> x.hasThisColour(colour));
    }

    private int[] getStartingIndicesForColour(Colour colour) {
        return (colour == Colour.WHITE) ? new int[]{0, size, 0, 1} : new int[]{0, 1, 0, size};
    }

    public boolean isOnBoard(Point point){
        return (0 <= point.x && point.x < size) && (0 <= point.y && point.y < size);
    }

    private int[] filterIndices(int startX, int endX, int startY, int endY) {
        return new int[]{Math.min(Math.max(0, startX), size - 1), Math.max(0, Math.min(size, endX)),
                Math.min(Math.max(0, startY), size - 1), Math.max(0, Math.min(size, endY))};
    }

    public boolean isOnEndingEdgeForColour(Point point, Colour colour) {
        return (colour == Colour.WHITE) ? point.x == size - 1 : point.y == size - 1;
    }

}
