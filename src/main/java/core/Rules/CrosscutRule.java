package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.io.ObjectInputFilter;
import java.util.stream.Stream;

public class CrosscutRule implements PositionRule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValidPosition(supervisor.getBoard(), supervisor.getCurrentPoint(), supervisor.getCurrentPlayer().getColour());
    }

    @Override
    public boolean isValidPosition(Board board, Point point, Colour colour) {
        //Point point = supervisor.getCurrentPoint();
        //Board board = supervisor.getBoard();
        //Colour colour = supervisor.getCurrentPlayer().getColour();
        Stream<Cell> neighbours = Neighbourhood.getNeighboursByPositionAndColour(board, point, colour, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColour(colour.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColour(colour.getOppositeColor()));
    }

}
