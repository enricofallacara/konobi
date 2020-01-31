package core;

import java.awt.*;
import java.util.ArrayList;

public class CrosscutRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        Board board = supervisor.getBoard();
        Player player = supervisor.getCurrentPlayer();
        Point point = supervisor.getCurrentPoint();
        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        if (neighbours.isEmpty()) { return true;}

        return neighbours.stream()
                .noneMatch(c ->
                        board.getCell(new Point(point.x, c.getCoordinates().y)).hasSameColorAsPlayer(supervisor.getLastPlayer())
                        &&
                        board.getCell(new Point(c.getCoordinates().x, point.y)).hasSameColorAsPlayer(supervisor.getLastPlayer()));
    }
}
