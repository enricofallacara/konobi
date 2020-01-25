package core;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidPositionRule {
    public static boolean query(Point p, Board board){

        return false;
    }

    public static List<Cell> getCertainNeighbours(Point point, Board board, Player player, Function<Point, Cell[]> function) {
        Cell[] neighbours = function.apply(point);
        return Arrays.stream(neighbours).filter(x -> x.getColor() == player.getColor()).collect(Collectors.toList());
    }

    public static boolean hasCertainNeighbours(Point point, Board board, Player player, Function<Point, Cell[]> function) {
        return getCertainNeighbours(point, board, player, function).size() > 0;
    }


    public static boolean hasCrosscut(Point point, Board board, Player player) {
        if( getCertainNeighbours(point, board, player, board::getWeakNeighbours).size() == 0 ) {
            return false;
        }

        Cell[] neighbours = board.getWeakNeighbours(point);
        for(Cell c : neighbours) {
            if(board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
              && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor()) {
                return true;
            }
        }
        return false;
    }

    public static boolean specialWeakPosition(Point point, Board board, Player player) {
        List<Cell> weakNeighbours = getCertainNeighbours(point, board, player, board::getWeakNeighbours);
        ArrayList<Boolean> result = new ArrayList<>();

        for(Cell c1 : weakNeighbours) {
            ArrayList<Cell> strongOfWeak = board.getStrongNeighbours(c1.getCoordinates());
            for(Cell c2 : strongOfWeak) {
                if( c2.getColor() == null ) {
                    result.add(hasCertainNeighbours(c2.getCoordinates(), board, player, board::getWeakNeighbours));
                }
            }
        }

        System.out.println(result);

        return result.stream().allMatch(x -> x == true);
    }




}
