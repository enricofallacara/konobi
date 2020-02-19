package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;

import java.awt.*;


public interface PositionRule extends Rule{

    @Override
    default boolean isValid(StatusSupervisor supervisor) {
        return isValidPosition(supervisor.getBoard(), supervisor.getCurrentPoint(), supervisor.getCurrentPlayer().getColour());
    }

    boolean isValidPosition(Board board, Point point, Colour colour);
}
