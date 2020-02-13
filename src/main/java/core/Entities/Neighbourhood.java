package core.Entities;

import java.awt.*;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.Stream;


public class Neighbourhood {

    public Neighbourhood( Board b){

    }
    // TODO: trovare un modo di fare il bounds check in Board, non qua
    public static Stream<Cell> getMooreNeighbours(Board board, Point p) {
        return Arrays.stream(
                board.slice(  Math.max(0, p.y - 1),
                        Math.min(p.y + 2, board.getSize()),
                        Math.max(0, p.x - 1),
                        Math.min(p.x + 2, board.getSize()))
        );
    }
    // TODO: getMooreNeighbours, getNeighbours e getColoredNeighbours potrebbero diventare non-statiche e la
    //  Board assegnata nel costruttore della classe. Non e fondamentale ma diventerebbe piu elegante
    // Risp: Non penso sia una buona idea perchè ciò implicherebbe una duplicazione della board,
    // cosa che credo dobbiamo evitare e che non sia necessaria. Per non parlare del fatto che dovremmo creare
    // un oggetto Neighbourhood ogni volta che chiamiamo una regola.

    public static boolean isStrongNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 1.0; }

    public static boolean isWeakNeighbour(Point target, Point query) { return manhattanDistance(target.x, query.x, target.y, query.y) == 2.0; }

    public static Stream<Cell> getNeighboursByType(Board board, Point point, BiPredicate<Point, Point> function) {
        return getMooreNeighbours(board, point).filter(cell -> function.test(point, cell.getCoordinates()));
    }

    public static Stream<Cell> getColoredNeighboursByType(Board board, Point point, Color color, BiPredicate<Point, Point> function) {
        return getNeighboursByType(board, point, function).filter(x -> x.hasThisColor(color));
    }

    private static double manhattanDistance(int x1, int x2, int y1, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2); }

}
