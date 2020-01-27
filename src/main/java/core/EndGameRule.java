package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EndGameRule {

    public static boolean query(Board board, Player player) {
        // TODO: NON SERVE UNA FUNZIONE PER OTTENERE I VICINI "POSITIVI", PER
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

    public static ArrayList<Cell> checkEndPoints(Board board, Player player) {
        // TODO: PROBLEMA: LONG METHOD
        // TODO: QUESTA FUNZIONE LASCIA MOLTO A DESIDERARE, SOPRATTUTTO NELL'ABUSO DEGLI ARRAYLIST
        // E NEL FLUSSO GENERALE
        ArrayList<Cell> start;
        ArrayList<Cell> end;
        Color color = player.getColor();
        int size = board.getSize();
        if (color == Color.black) {
            start = new ArrayList<>(Arrays.asList(board.slice(0, size, 0, 1)));//getColumn(0);
            end = new ArrayList<>(Arrays.asList(board.slice(0, size, size - 1, size)));//getColumn(board.getSize() - 1);
        }
        else {
            start = new ArrayList<>(Arrays.asList(board.slice(0, 1, 0, size)));//getRow(0);
            end = new ArrayList<>(Arrays.asList(board.slice(0, 1, size - 1, size)));//getRow(board.getSize() - 1);
        }
        start = start.stream().filter(x -> x.getColor() == color).collect(Collectors.toCollection(ArrayList::new));
        end = end.stream().filter(x -> x.getColor() == color).collect(Collectors.toCollection(ArrayList::new));
        if (start.size() == 0 || end.size() == 0) {
            return new ArrayList<>();
        }
        return start;
    }

    private boolean propagate(Cell current, Player player) {
        return false;
    }

}
