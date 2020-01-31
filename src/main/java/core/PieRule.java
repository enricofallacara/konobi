package core;


public class PieRule implements Rule{
    @Override
    public boolean isValid(Supervisor supervisor) {return isValid(supervisor.getTurn());}
    public static boolean isValid(int nTurn){return nTurn == 2;}
}
