package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;

public class CrosscutRule implements Rule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer().getColour());
    }

    public static boolean isValid(Point point, Board board, Colour colour) {

        Stream<Cell> neighbours = Neighbourhood.getColouredNeighboursByType(board, point, colour, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColor(colour.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColor(colour.getOppositeColor()));
    }

}
