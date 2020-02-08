package core.Rules;

import core.Entities.*;

import java.awt.Point;
import java.util.stream.Stream;

public class CrosscutRule implements Rule {
    // TODO: passare a isValid interno solamente il colore
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer().getColor());
    }
    // TODO: racchiudere la condizione sulla cella in una funzione ausiliaria
    public static boolean isValid(Point point, Board board, Color color) {
        Stream<Cell> neighbours = board.getColoredNeighbours(point, 1, color, Board::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColor(color.getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColor(color.getOppositeColor()));
    }

}
