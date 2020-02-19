package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;

import java.awt.*;


public class EmptyRule implements PositionRule {

    @Override
    public boolean isValidPosition(Board board, Point point, Colour colour) {
        return !board.getCell(point).hasThisColour(colour) && !board.getCell(point).hasThisColour(colour.getOppositeColor());
    }

}
