package core;

import java.awt.*;
import java.util.ArrayList;

public class CrosscutRule implements PositionRule {
    @Override
    public boolean isValid(Point point, Board board, Player player) {
        ArrayList<Cell> neighbours = board.getColoredNeighbours(point, 1, player, Board::isWeakNeighbour);
        if (neighbours.isEmpty()) {
            return true;
        }  // TODO: controllare se questo puo essere leggibile
        return neighbours.stream().allMatch(c -> board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
                && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor());
        /*for(Cell c : neighbours) {
            if(board.getCell(new Point(point.x, c.getCoordinates().y)).getColor() == player.getOppositeColor()
                    && board.getCell(new Point(c.getCoordinates().x, point.y)).getColor() == player.getOppositeColor()) {
                return false;
            }
        }
        return true;*/
    }
}
