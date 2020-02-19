package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;


public class WeakRule implements PositionRule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValidPosition(supervisor.getBoard(), supervisor.getCurrentPoint(), supervisor.getCurrentPlayer().getColour());
    }

    @Override
    public boolean isValidPosition(Board board, Point point, Colour colour) {
        //Point point = supervisor.getCurrentPoint();
        //Board board = supervisor.getBoard();
        //Colour colour = supervisor.getCurrentPlayer().getColour();
        Stream<Cell> weakNeighbours = Neighbourhood.getNeighboursByPositionAndColour(board, point, colour, Neighbourhood::isWeakNeighbour);
        return weakNeighbours.flatMap(c1 -> Neighbourhood.getNeighboursByPosition(board, c1.getCoordinates(), Neighbourhood::isStrongNeighbour)
                .filter(c2 -> c2.hasThisColour(null))
                .map(c3 -> Neighbourhood.getNeighboursByPositionAndColour(board, c3.getCoordinates(), colour, Neighbourhood::isWeakNeighbour)
                        .findAny().isPresent()))
                .allMatch(b -> b);
    }

}
