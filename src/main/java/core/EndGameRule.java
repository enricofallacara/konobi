package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EndGameRule {

    public static boolean query(Board board, Player player) {
        // NON SERVE UNA FUNZIONE PER OTTENERE I VICINI "POSITIVI", PER
        // DUE MOTIVI: 1) NON E DETTO CHE UNA CATENA SI SVILUPPI SOLAMENTE
        // "IN AVANTI"; 2) SI PUO USARE UNA STRUTTURA DATI (COME UNA HASHTABLE)
        // PER RICORDARE CHI E STATO ATTRAVERSATO E CHI NO.
        /*ArrayList<Cell> endpoints = checkEndPoints(board, player);
        if (endpoints.size() == 0) {
            return false;
        }
        for (Cell startpoint : endpoints) {
        }*/
        return false;
    }

    /*public static ArrayList<Cell> checkEndPoints(Board board, Player player) {
        // PROBLEMA: LONG METHOD
        ArrayList<Cell> start;
        ArrayList<Cell> end;
        Color color = player.getColor();
        if (color == Color.black) {
            start = board.slice();//getColumn(0);
            end = board.getColumn(board.getSize() - 1);
        }
        else {
            start = board.getRow(0);
            end = board.getRow(board.getSize() - 1);
        }
        start = start.stream().filter(x -> x.getColor() == color).collect(Collectors.toCollection(ArrayList::new));
        end = end.stream().filter(x -> x.getColor() == color).collect(Collectors.toCollection(ArrayList::new));
        if (start.size() == 0 || end.size() == 0) {
            return new ArrayList<>();
        }
        return start;
    }*/

    private boolean propagate(Cell current, Player player) {
        return false;
    }

}
