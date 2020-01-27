package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EndGameRule {

    public static boolean query(Board board, Player player) {
        // TODO: NON SERVE UNA FUNZIONE PER OTTENERE I VICINI "POSITIVI", PER
        // DUE MOTIVI: 1) NON E DETTO CHE UNA CATENA SI SVILUPPI SOLAMENTE
        // "IN AVANTI"; 2) SI PUO USARE UNA STRUTTURA DATI (COME UNA HASHTABLE)
        // PER RICORDARE CHI E STATO ATTRAVERSATO E CHI NO.
        /*ArrayList<Cell> endpoints = getEndPoints(board, player);
        if (endpoints.size() == 0) {
            return false;
        }
        for (Cell startpoint : endpoints) {
        }*/
        return false;
    }

    public static ArrayList<Cell> getEndPoints(Board board, Player player) {
        Color color = player.getColor();
        int size = board.getSize();
        int[] startIdxs = (color == Color.black) ? new int[]{0, size, 0, 1} : new int[]{0, 1, 0, size};
        return Arrays.stream(board.slice(startIdxs[0], startIdxs[1], startIdxs[2], startIdxs[3])).
                filter(x -> x.getColor() == color).collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean propagate(Cell current, Player player) {
        return false;
    }

}
