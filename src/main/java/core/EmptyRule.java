package core;

import java.awt.*;
import java.util.ArrayList;

public class EmptyRule implements PositionRule {
    public boolean isValid(Point point, Board board, Player player) {
        return board.getCell(point).getColor() == null;
    }
}

