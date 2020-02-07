package core.Rules;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Player;
import core.Entities.Supervisor;
import java.awt.Point;
import java.util.stream.Stream;

public class WeakRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer());
    }

    public static boolean isValid(Point point, Board board, Player player) {
        Stream<Cell> weakNeighbours = board.getColoredNeighbours(
                                                point, 1, player, Board::isWeakNeighbour);
        return weakNeighbours.flatMap(
                        c1 -> board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour)
                                .filter(c2 -> c2.hasThisColor(null))
                                .map(c3 -> board.getColoredNeighbours(c3.getCoordinates(), 1, player, Board::isWeakNeighbour)
                                        .findAny().isPresent()))
                .allMatch(b -> b);
    }
}
