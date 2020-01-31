package core;

import java.util.ArrayList;

public class WeakRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        Board board = supervisor.getBoard();
        Player player = supervisor.getCurrentPlayer();
        ArrayList<Cell> weakNeighbours = board.getColoredNeighbours(
                                                supervisor.getCurrentPoint(), 1, player, Board::isWeakNeighbour);
        if (weakNeighbours.isEmpty()) {
            return true;
        }
        return weakNeighbours.stream()
                .flatMap(
                        c1 -> board.getNeighbours(c1.getCoordinates(), 1, Board::isStrongNeighbour)
                                .stream().filter(c2 -> c2.getColor() == null)
                                .map(c3 -> !board.getColoredNeighbours(c3.getCoordinates(), 1, player, Board::isWeakNeighbour)
                                        .isEmpty()))
                .allMatch(b -> b);

    }
}
