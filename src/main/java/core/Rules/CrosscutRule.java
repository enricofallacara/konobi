package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;


public class CrosscutRule implements PositionRule {

    @Override
    public boolean isValidPosition(Board board, Point point, Colour colour) {
        Stream<Cell> neighbours = Neighbourhood.getNeighboursByPositionAndColour(board, point, colour, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColour(colour.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColour(colour.getOppositeColor()));
    }

}
