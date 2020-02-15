package core.Entities;

import java.awt.*;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.Stream;


public class Neighbourhood {

    /*public Neighbourhood( Board b){

    }*/

    public static Stream<Cell> getMooreNeighbours(Board board, Point p) {
        return Arrays.stream(board.slice(p.y - 1, p.y + 2, p.x - 1, p.x + 2));
    }
    // TODO: getMooreNeighbours, getNeighbours e getColouredNeighbours potrebbero diventare non-statiche e la
    //  Board assegnata nel costruttore della classe. Non e fondamentale ma diventerebbe piu elegante
    // TODO: chiedere ai prof

    public static boolean isStrongNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 1.0; }

    public static boolean isWeakNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 2.0; }

    public static Stream<Cell> getNeighboursByType(Board board, Point point, BiPredicate<Point, Point> function) {
        return getMooreNeighbours(board, point).filter(cell -> function.test(point, cell.getCoordinates()));
    }

    public static Stream<Cell> getColouredNeighboursByType(Board board, Point point, Colour colour, BiPredicate<Point, Point> function) {
        return getNeighboursByType(board, point, function).filter(x -> x.hasThisColour(colour));
    }

    private static double manhattanDistance(int x1, int x2, int y1, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2); }

}
