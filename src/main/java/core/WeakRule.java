package core;

import java.awt.*;
import java.util.ArrayList;

public class WeakRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer());
    }

    public static boolean isValid(Point point, Board board, Player player) {
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(
                                                point, 1, player, Board::isWeakNeighbour);
        if (weakNeighbours.isEmpty()) {
            return true;
        }
        return weakNeighbours.stream()
                .flatMap(
                        c1 -> board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour)
                                .stream().filter(c2 -> c2.hasThisColor(null))
                                .map(c3 -> !board.getColoredNeighbours(c3.getCoordinates(), 1, player, Board::isWeakNeighbour)
                                        .isEmpty()))
                .allMatch(b -> b);
    }
}
