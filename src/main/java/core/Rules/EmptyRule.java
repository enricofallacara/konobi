package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;

import java.awt.*;


public class EmptyRule implements PositionRule {

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return isValidPosition(supervisor.getBoard(), supervisor.getCurrentPoint(), supervisor.getCurrentPlayer().getColour());
    }

    @Override
    public boolean isValidPosition(Board board, Point point, Colour colour) {
        return !board.getCell(point).hasThisColour(colour) && !board.getCell(point).hasThisColour(colour.getOppositeColor());
    }

}
