package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;

import java.awt.*;

public interface PositionRule extends Rule{

    boolean isValidPosition(Board board, Point point, Colour colour);
}
