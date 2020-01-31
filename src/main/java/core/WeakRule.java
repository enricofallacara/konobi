package core;

import java.awt.*;
import java.util.ArrayList;

public class WeakRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        Board board = supervisor.getBoard();
        Player player = supervisor.getCurrentPlayer();
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(supervisor.getCurrentPoint(), 1, player, Board::isWeakNeighbour);
        if (weakNeighbours.isEmpty()) {
            // If it has no weak neighbours.
            return true;
        } // TODO: controllare se questo puo essere leggibile
        return weakNeighbours.stream().flatMap(c1 -> board.getNeighbours(c1.getCoordinates(), 1,
                Board::isStrongNeighbour).stream().filter(c2 -> c2.getColor() == null).map(c3 -> !board.
                getColoredNeighbours(c3.getCoordinates(), 1, player, Board::isWeakNeighbour).isEmpty())).allMatch(b -> b);
        /*} else {
            // Else (some weak connections), check they are special weaks.
            ArrayList<Boolean> result = new ArrayList<>();

            for (Cell c1 : weakNeighbours) {
                ArrayList<Cell> strongOfWeak = board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour);
                for (Cell c2 : strongOfWeak) {
                    if (c2.getColor() == null) {
                        result.add(!board.getColoredNeighbours(c2.getCoordinates(), 1, player, Board::isWeakNeighbour).isEmpty());

                    }
                }
            }*/
            //return result.stream().allMatch(x -> x);
 //       }
    }
}
