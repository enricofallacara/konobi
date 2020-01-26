package core;

import java.awt.*;
import java.util.ArrayList;

public class ValidPositionRule {
    public static boolean query(Point point, Board board, Player player){
        if (hasCrosscut(point, board, player)) {
            return false;
        }
        else if (!board.getColoredNeighbours(point, player, board::getStrongNeighbours).isEmpty()) {
            return true;
        }
        else if (specialWeakPosition(point, board, player)) {
            return true;
        }
        else if (!board.getColoredNeighbours(point, player, board::getWeakNeighbours).isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean hasCrosscut(Point point, Board board, Player player) {
        if(board.getColoredNeighbours(point, player, board::getWeakNeighbours).isEmpty()) {
            return false;
        }

        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, player, board::getWeakNeighbours);
        for(Cell c : neighbours) {
            if(board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
                    && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor()) {
                return true;
            }
        }
        return false;
    }

    public static boolean specialWeakPosition(Point point, Board board, Player player) {
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(point, player, board::getWeakNeighbours);
        ArrayList<Boolean> result = new ArrayList<>();

        for(Cell c1 : weakNeighbours) {
            ArrayList<Cell> strongOfWeak = board.getNeighbours(c1.getCoordinates(), board::getStrongNeighbours);
            for(Cell c2 : strongOfWeak) {
                if( c2.getColor() == null ) {
                    result.add(!board.getColoredNeighbours(c2.getCoordinates(), player, board::getWeakNeighbours).isEmpty());
                }
            }
        }
        return result.stream().allMatch(x -> x == true);
    }


}




