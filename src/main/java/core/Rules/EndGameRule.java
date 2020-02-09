package core.Rules;

import core.Entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class EndGameRule implements Rule{
    private HashSet<Cell> table;
    private ArrayList<Cell> endingList;

    public EndGameRule(){
        table = new HashSet<>();
    }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getBoard(), supervisor.getLastPlayer());
    }

    public boolean isValid(Board board, Player player) {
        System.out.println("\n" + player.getColor() + " plays\n");
        ArrayList<Cell> startingPoints = getStartingPoints(board, player);
        if (startingPoints.isEmpty()) { return false;}
        table.clear();
        endingList = new ArrayList<>();
        System.out.println("Looking for end points");
        startingPoints.forEach(x -> searchForEndingEdge(x, board, player));
        return !endingList.isEmpty();
        //return startingPoints.stream().anyMatch(x -> searchForEndingEdge(x, board, player));
    }

    public ArrayList<Cell> getStartingPoints(Board board, Player player) {
        Color color = player.getColor();
        int size = board.getSize();
        int[] startIdxs = (color == Color.white) ? new int[]{0, size, 0, 1} :  new int[]{0, 1, 0, size};

        //new
        ArrayList<Cell> cc = Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                             filter(x -> x.hasThisColor(color)).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Starting points");
        for (Cell c : cc) {
            System.out.println(c.getCoordinates().x + " " + c.getCoordinates().y);
        }

        return cc;
        /*
        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                filter(x -> x.hasThisColor(color)).collect(Collectors.toCollection(ArrayList::new));
         */
    }

    //public boolean searchForEndingEdge(Cell current, Board board, Player player) {
    public void searchForEndingEdge(Cell current, Board board, Player player) {
        if (board.isOnEndingEdge(current.getCoordinates(), player)) {
            System.out.println("Ending edge: " + current.getCoordinates().x + " " + current.getCoordinates().y);
            endingList.add(current);
            return;
            //return true;
        }
        table.add(current);
        System.out.println("Neigh of " + current.getCoordinates().x + " " + current.getCoordinates().y);
        for (Cell neighbour : board.getColoredNeighbours(current.getCoordinates(), 1, player, (x, y) -> true).toArray(Cell[]::new)) {
            if (!table.contains(neighbour)) {
                System.out.println(neighbour.getCoordinates().x + " " + neighbour.getCoordinates().y);
                //return searchForEndingEdge(neighbour, board, player);
                searchForEndingEdge(neighbour, board, player);
            }
        }
        //return false;
    }

}
