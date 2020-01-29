package core;

import java.util.stream.StreamSupport;

public class PassRule {
    public static boolean query(Board board,Player player){
        return StreamSupport.stream(board.spliterator(), false).
                noneMatch(x -> Rulebook.queryValidPosition(x.getCoordinates(), board, player));
    }
}
