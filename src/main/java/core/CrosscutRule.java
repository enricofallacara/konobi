package core;

import java.awt.*;
import java.util.ArrayList;

public class CrosscutRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard(), supervisor.getLastPlayer());
    }

    public static boolean isValid(Point point, Board board, Player player) {
        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        if (neighbours.isEmpty()) { return true;}

        return neighbours.stream()
                .noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasSameColorAsPlayer(player)
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasSameColorAsPlayer(player));
    }
}
