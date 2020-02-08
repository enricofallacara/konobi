package core.Rules;

import core.Entities.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class EndGameRule implements Rule{
    private HashSet<Cell> visitedTable;

    public EndGameRule(){ visitedTable = new HashSet<>(); }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getBoard(), supervisor.getLastPlayer().getColor());
    }

    public boolean isValid(Board board, Color color) {
        return getStartingPoints(board, color).anyMatch(x -> searchForEndingEdge(x, board, color));
    }

    public Stream<Cell> getStartingPoints(Board board, Color color) {
        int size = board.getSize();
        int[] startIdxs = (color == Color.white) ? new int[]{0, size, 0, 1} :  new int[]{0, 1, 0, size};
        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                filter(x -> x.hasThisColor(color));
    }

    public boolean searchForEndingEdge(Cell current, Board board, Color color) {
        if (board.isOnEndingEdge(current.getCoordinates(), color)) { return true; }
        visitedTable.add(current);
        for (Cell neighbour : board.getColoredNeighbours(current.getCoordinates(), 1, color, (x, y) -> true).toArray(Cell[]::new)) {
            if (!visitedTable.contains(neighbour)) { return searchForEndingEdge(neighbour, board, color); }
        }
        return false;
    }

}
