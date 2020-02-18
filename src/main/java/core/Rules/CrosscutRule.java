package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;

public class CrosscutRule implements PositionRule {

    @Override
    public boolean isValidPosition(StatusSupervisor supervisor) {
        Point point = supervisor.getCurrentPoint();
        Board board = supervisor.getBoard();
        Colour colour = supervisor.getCurrentPlayer().getColour();
        Stream<Cell> neighbours = Neighbourhood.getColouredNeighboursByType(board, point, colour, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColour(colour.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColour(colour.getOppositeColor()));
    }

    /*public static boolean isValid(Point point, Board board, Colour colour) {

        Stream<Cell> neighbours = Neighbourhood.getColouredNeighboursByType(board, point, colour, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColor(colour.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColor(colour.getOppositeColor()));
    }*/

}
