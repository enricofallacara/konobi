package core.Rules;

import core.Entities.StatusSupervisor;


public class PieRule implements Rule{

    @Override
    public boolean isValid(StatusSupervisor supervisor) {return supervisor.getTurn() == 2;}

    //public static boolean isValid(int nTurn){return nTurn == 2;}

}
