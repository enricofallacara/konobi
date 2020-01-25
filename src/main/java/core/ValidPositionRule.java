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

    //public static boolean hasStrong(Point point, Board board, Player player){
        //Cell[] strongNeighbours = board.getStrongNeighbours(point);
        //for(Cell c : strongNeighbours) {
            //if(c.getColor() == player.getColor()) {
                //return true;
            //}
        //}
    //return false;
    //}

    //public static boolean hasWeak(Point point, Board board, Player player){
        //Cell[] weakNeighbours = board.getWeakNeighbours(point);
        //for(Cell c : weakNeighbours) {
            //if(c.getColor() == player.getColor()) {
                //return true;
            //}
        //}
        //return false;
   //}



}
