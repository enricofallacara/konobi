package core.Rules;

import core.Entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class EndGameRule implements Rule{
    private HashSet<Cell> table;

    public EndGameRule(){
        table = new HashSet<>();
    }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getBoard(), supervisor.getLastPlayer());
    }

    public boolean isValid(Board board, Player player) {
        ArrayList<Cell> startingPoints = getStartingPoints(board, player);
        if (startingPoints.isEmpty()) { return false;}
        table.clear();
        return startingPoints.stream().anyMatch(x -> searchForEndingEdge(x, board, player));
    }

    public ArrayList<Cell> getStartingPoints(Board board, Player player) {
        Color color = player.getColor();
        int size = board.getSize();
        int[] startIdxs = (color == Color.white) ? new int[]{0, size, 0, 1} :  new int[]{0, 1, 0, size};
        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                filter(x -> x.hasThisColor(color)).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean searchForEndingEdge(Cell current, Board board, Player player) {
        if (board.isOnEndingEdge(current.getCoordinates(), player)) { return true; }
        table.add(current);
        for (Cell neighbour : board.getColoredNeighbours(current.getCoordinates(), 1, player, (x, y) -> true)) {
           if (!table.contains(neighbour)) { return searchForEndingEdge(neighbour, board, player); }
        }
        return false;
    }

}