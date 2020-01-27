package core;

import java.awt.*;
import java.util.ArrayList;


public class ValidPositionRule {
    public static boolean query(ArrayList<PositionRule> rules, Point point, Board board, Player player) {
        /*

        ArrayList<Boolean> result = new ArrayList<>();

        rules.stream().forEach(x -> x.isValid(point, board, player));

        for(PositionRule rule : rules) {
            result.add(rule.isValid(point, board, player));
        }

        return result.stream().allMatch(x -> x);

         */

        return rules.stream().allMatch(x -> x.isValid(point, board, player));

    }

}





/*

public class ValidPositionRule {
    public static boolean query(Point point, Board board, Player player){
        // TODO: PROBLEMA: SE QUALCUNO VUOLE AGGIUNGERE UNA REGOLA, DEVE AGGIUNGERE UN ELSE IF,
        // CHE RENDE IL CODICE PIU RIGIDO E VIOLA L'OPEN CLOSED PRINCIPLE.
        // TODO: LA SOLUZIONE E LA CLASSE RULEBOOK
        if (hasCrosscut(point, board, player)) {
            return false;
        }
        else if (!board.getColoredNeighbours(point, 1, player, Board::isStrongNeighbour).isEmpty()) {
            return true;
        }
        else if (specialWeakPosition(point, board, player)) {
            return true;
        }
        else if (!board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty()) {
            return false;
        }
        return true;
    }


    public static boolean hasCrosscut(Point point, Board board, Player player) {
        if(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty()) {
            return false;
        }

        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        for(Cell c : neighbours) {
            if(board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
                    && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor()) {
                return true;
            }
        }
        return false;
    }

    public static boolean specialWeakPosition(Point point, Board board, Player player) {
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        ArrayList<Boolean> result = new ArrayList<>();

        for(Cell c1 : weakNeighbours) {
            ArrayList<Cell> strongOfWeak = board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour);
            for(Cell c2 : strongOfWeak) {
                if( c2.getColor() == null ) {
                    result.add(!board.getColoredNeighbours(c2.getCoordinates(), 1, player, Board::isWeakNeighbour).isEmpty());
                }
            }
        }
        return result.stream().allMatch(x -> x == true);
    }

}

 */
