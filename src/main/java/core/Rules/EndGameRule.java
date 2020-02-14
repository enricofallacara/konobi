package core.Rules;

import core.Entities.*;

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
        board.getStartingCells(color).forEach(x -> searchForEndingEdge(x, board, color));
        return !endingCells.isEmpty();
    }

    public void searchForEndingEdge(Cell current, Board board, Color color) {
        if (board.isOnEndingEdge(current.getCoordinates(), color)) {
            endingCells.add(current);
            return;
        }
        visitedCells.add(current);
        for (Cell neighbour : Neighbourhood.getColoredNeighboursByType(board, current.getCoordinates(), color, (x, y) -> true).toArray(Cell[]::new)) {
            if (!visitedCells.contains(neighbour)) {
                searchForEndingEdge(neighbour, board, color);
            }
        }
    }

}
