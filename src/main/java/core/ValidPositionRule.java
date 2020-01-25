package core;

import java.awt.*;
import java.util.function.Function;

public class ValidPositionRule {
    public static boolean query(Point p, Board board){

        return false;
    }

    public static boolean hasCertainNeighbours(Point point, Board board, Player player, Function<Point, Cell[]> function) {
        Cell[] neighbours = function.apply(point);
        for(Cell c : neighbours) {
            if(c.getColor() == player.getColor()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCrosscut(Point point, Board board, Player player) {
        if( ! hasCertainNeighbours(point, board, player, board::getWeakNeighbours)) {
            return false;
        }

        Cell[] neighbours = board.getWeakNeighbours(point);
        for(Cell c : neighbours) {
            if(

                    c.getColor() == player.getColor()) {
                return true;
            }
        }
        return false;
    }




}
