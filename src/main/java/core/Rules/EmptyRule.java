package core.Rules;

import core.Entities.StatusSupervisor;


public class EmptyRule implements Rule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return supervisor.getBoard().getCell(supervisor.getCurrentPoint()).hasThisColour(null);
    }

   // public static boolean isValid(Point point, Board board) {
       // return board.getCell(point).hasThisColor(null);
    //}

}

