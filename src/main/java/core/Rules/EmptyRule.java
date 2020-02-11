package core.Rules;

import core.Entities.Board;
import core.Entities.StatusSupervisor;

import java.awt.Point;

public class EmptyRule implements Rule {
    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard());
    }

    public static boolean isValid(Point point, Board board) {
        return board.getCell(point).getColor() == null;
    }
}

