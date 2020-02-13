package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;


public class WeakRule implements Rule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer().getColor());
    }

    public static boolean isValid(Point point, Board board, Color color) {
        Stream<Cell> weakNeighbours = Neighbourhood.getColoredNeighboursByType(board, point, color, Neighbourhood::isWeakNeighbour);
        return weakNeighbours.flatMap(c1 -> Neighbourhood.getNeighboursByType(board, c1.getCoordinates(), Neighbourhood::isStrongNeighbour)
                                            .filter(c2 -> c2.hasThisColor(null))
                                            .map(c3 -> Neighbourhood.getColoredNeighboursByType(board, c3.getCoordinates(), color, Neighbourhood::isWeakNeighbour)
                                                            .findAny().isPresent()))
                .allMatch(b -> b);
    }

}
