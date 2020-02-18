package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;


public class WeakRule implements PositionRule {

    @Override
    public boolean isValidPosition(StatusSupervisor supervisor) {
        Point point = supervisor.getCurrentPoint();
        Board board = supervisor.getBoard();
        Colour colour = supervisor.getCurrentPlayer().getColour();
        Stream<Cell> weakNeighbours = Neighbourhood.getColouredNeighboursByType(board, point, colour, Neighbourhood::isWeakNeighbour);
        return weakNeighbours.flatMap(c1 -> Neighbourhood.getNeighboursByType(board, c1.getCoordinates(), Neighbourhood::isStrongNeighbour)
                .filter(c2 -> c2.hasThisColour(null))
                .map(c3 -> Neighbourhood.getColouredNeighboursByType(board, c3.getCoordinates(), colour, Neighbourhood::isWeakNeighbour)
                        .findAny().isPresent()))
                .allMatch(b -> b);
    }

    /*public static boolean isValid(Point point, Board board, Colour colour) {
        Stream<Cell> weakNeighbours = Neighbourhood.getColouredNeighboursByType(board, point, colour, Neighbourhood::isWeakNeighbour);
        return weakNeighbours.flatMap(c1 -> Neighbourhood.getNeighboursByType(board, c1.getCoordinates(), Neighbourhood::isStrongNeighbour)
                                            .filter(c2 -> c2.hasThisColor(null))
                                            .map(c3 -> Neighbourhood.getColouredNeighboursByType(board, c3.getCoordinates(), colour, Neighbourhood::isWeakNeighbour)
                                                            .findAny().isPresent()))
                .allMatch(b -> b);
    }*/

}
