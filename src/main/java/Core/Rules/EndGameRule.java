package Core.Rules;

import Core.Entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class EndGameRule implements Rule {
    private final HashSet<Cell> visitedCells;
    private final ArrayList<Cell> endingCells;

    public EndGameRule(){
        visitedCells = new HashSet<>();
        endingCells = new ArrayList<>();
    }

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValid(supervisor.getBoard(), supervisor.getLastPlayer().getColor());
    }

    public boolean isValid(Board board, Color color) {
        getStartingCells(board, color).forEach(x -> searchForEndingEdge(x, board, color));
        return !endingCells.isEmpty();
    }

    public Stream<Cell> getStartingCells(Board board, Color color) {
        int size = board.getSize();
        int[] startIdxs = (color == Color.white) ? new int[]{0, size, 0, 1} : new int[]{0, 1, 0, size};
        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                             filter(x -> x.hasThisColor(color));
    }

    public void searchForEndingEdge(Cell current, Board board, Color color) {
        if (board.isOnEndingEdge(current.getCoordinates(), color)) {
            endingCells.add(current);
            return;
        }
        visitedCells.add(current);
        for (Cell neighbour : Neighbourhood.getColoredNeighbours(board, current.getCoordinates(), 1, color, (x, y) -> true).toArray(Cell[]::new)) {
            if (!visitedCells.contains(neighbour)) {
                searchForEndingEdge(neighbour, board, color);
            }
        }
    }

}
