package core;

public class PassRule {
    public static boolean query(Board board,Player player){
        for(Cell c: board) {
            if(Rulebook.getPositionRules().stream().allMatch(x -> x.isValid(c.getCoordinates(), board, player))){
                return false;
            }
        }
        return true;
    }
}
