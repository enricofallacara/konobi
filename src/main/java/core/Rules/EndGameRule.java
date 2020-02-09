package core.Rules;

import core.Entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class EndGameRule implements Rule {

    private HashSet<Cell> visited;
    private ArrayList<Cell> endingPoints;

    public EndGameRule(){
        visited = new HashSet<>();
        endingPoints = new ArrayList<>();
    }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getBoard(), supervisor.getLastPlayer());
    }

    public boolean isValid(Board board, Player player) {
        // TODO: al di là dei test, creiamo un oggetto ogni volta: ha senso tenere i clear?
        visited.clear();
        endingPoints.clear();
        getStartingPoints(board, player).forEach(x -> searchForEndingEdge(x, board, player));
        return !endingPoints.isEmpty();
    }

    public Stream<Cell> getStartingPoints(Board board, Player player) {
        Color color = player.getColor();
        int size = board.getSize();
        int[] startIdxs = (color == Color.white) ? new int[]{0, size, 0, 1} : new int[]{0, 1, 0, size};

        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                             filter(x -> x.hasThisColor(color));
    }

    public void searchForEndingEdge(Cell current, Board board, Player player) {
        if (board.isOnEndingEdge(current.getCoordinates(), player)) {
            endingPoints.add(current);
            return;
        }
        visited.add(current);
        for (Cell neighbour : board.getColoredNeighbours(current.getCoordinates(), 1, player, (x, y) -> true).toArray(Cell[]::new)) {
            if (!visited.contains(neighbour)) {
                searchForEndingEdge(neighbour, board, player);
            }
        }
    }

}
