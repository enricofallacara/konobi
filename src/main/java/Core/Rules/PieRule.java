package Core.Rules;


import Core.Entities.StatusSupervisor;

public class PieRule implements Rule{
    @Override
    public boolean isValid(StatusSupervisor supervisor) {return isValid(supervisor.getTurn());}
    public static boolean isValid(int nTurn){return nTurn == 2;}
}
