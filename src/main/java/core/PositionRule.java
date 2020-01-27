package core;

import java.awt.*;

public interface PositionRule {
    boolean isValid(Point point, Board board, Player player);
}
