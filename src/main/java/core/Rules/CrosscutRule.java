package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;

public class CrosscutRule implements Rule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer().getColor());
    }

    public static boolean isValid(Point point, Board board, Color color) {
        Stream<Cell> neighbours = Neighbourhood.getColoredNeighbours(board, point, color, Neighbourhood::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColor(color.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColor(color.getOppositeColor()));
    }

}
