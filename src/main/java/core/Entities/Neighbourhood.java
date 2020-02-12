package core.Entities;

import java.awt.*;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.Stream;


public class Neighbourhood {
    // TODO: trovare un modo di fare il bounds check in Board, non qua
    // TODO: si potrebbe togliere il parametro level, in quanto usiamo sempre 1
    //  e complica un poco il codice
    public static Stream<Cell> getMooreNeighbours(Board board, Point p, int level) {
        return Arrays.stream(
                board.slice(  Math.max(0, p.y - level),
                        Math.min(p.y + level + 1, board.getSize()),
                        Math.max(0, p.x - level),
                        Math.min(p.x + level + 1, board.getSize()))
        );
    }
    // TODO: getMooreNeighbours, getNeighbours e getColoredNeighbours potrebbero diventare non-statiche e la
    //  Board assegnata nel costruttore della classe. Non e fondamentale ma diventerebbe piu elegante
    // TODO: rendere i nomi piu espressivi, per esempio getNeighbours potrebbe diventare filterNeighboursByPosition
    public static boolean isStrongNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 1.0; }

    public static boolean isWeakNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 2.0; }

    public static Stream<Cell> getNeighbours(Board board, Point point, int level, BiPredicate<Point, Point> function) {
        return getMooreNeighbours(board, point, level).filter(cell -> function.test(point, cell.getCoordinates()));
    }

    public static Stream<Cell> getColoredNeighbours(Board board, Point point, int level, Color color, BiPredicate<Point, Point> function) {
        return getNeighbours(board, point, level, function).filter(x -> x.hasThisColor(color));
    }

    private static double manhattanDistance(int x1, int x2, int y1, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2); }

}
