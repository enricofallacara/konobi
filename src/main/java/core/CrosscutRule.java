package core;

        import java.awt.*;
        import java.util.ArrayList;

public class CrosscutRule implements PositionRule {
    @Override
    public boolean isValid(Point point, Board board, Player player) {
        if(board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour).isEmpty()) {
            return true;
        }

        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        for(Cell c : neighbours) {
            if(board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
                    && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor()) {
                return false;
            }
        }
        return true;
    }
}
