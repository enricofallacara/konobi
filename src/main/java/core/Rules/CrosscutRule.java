package core.Rules;

import core.Entities.Board;
import core.Entities.Cell;
import core.Entities.Player;
import core.Entities.Supervisor;
import java.awt.Point;
import java.util.stream.Stream;

public class CrosscutRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getCurrentPlayer());
    }

    public static boolean isValid(Point point, Board board, Player player) {
        Stream<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        return neighbours.noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasThisColor(player.getColor().getOppositeColor())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasThisColor(player.getColor().getOppositeColor()));
    }
}
