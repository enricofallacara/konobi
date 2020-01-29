package core;

import java.util.stream.StreamSupport;

public class PassRule {
    public static boolean query(Board board,Player player){
        return StreamSupport.stream(board.spliterator(), false).
                anyMatch(x -> !Rulebook.queryValidPosition(x.getCoordinates(), board, player));
        /*for(Cell c: board) {
            if(Rulebook.queryValidPosition(c.getCoordinates(), board, player)){
                return false;
            }
        }
        return true;*/
    }
}
