package core;

import java.awt.*;
import java.util.ArrayList;

public class WeakRule implements PositionRule {
    @Override
    public boolean isValid(Point point, Board board, Player player) {
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        if(weakNeighbours.isEmpty()) {
            // If it has no weak neighbours.
            return true;
        } else {
            // Else (some weak connections), check they are special weaks.
            ArrayList<Boolean> result = new ArrayList<>();

            for (Cell c1 : weakNeighbours) {
                ArrayList<Cell> strongOfWeak = board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour);
                for (Cell c2 : strongOfWeak) {
                    if (c2.getColor() == null) {
                        result.add(!board.getColoredNeighbours(c2.getCoordinates(), 1, player, Board::isWeakNeighbour).isEmpty());

                    }
                }
            }
            return result.stream().allMatch(x -> x == true);
        }
    }
}
