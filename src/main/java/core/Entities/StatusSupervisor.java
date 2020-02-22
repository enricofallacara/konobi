package core.Entities;

import core.Rules.ValidPositionRule;
import java.awt.Point;


public class StatusSupervisor {

    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private Point currentPoint;
    private Colour currentColour;
    private int nTurn;

    public StatusSupervisor(int boardSize) {
        board = new Board(boardSize);
        playerOne = new Player(Colour.BLACK, "playerOne");
        playerTwo = new Player(Colour.WHITE, "playerTwo");
        currentColour = Colour.BLACK;
        nTurn = 1;
    }

    public Board getBoard() { return board; }

    public Player getCurrentPlayer() { return (playerOne.getColour() == currentColour) ? playerOne : playerTwo; }

    public Player getLastPlayer() { return (playerOne.getColour() == currentColour) ? playerTwo : playerOne; }

    public Point getCurrentPoint() { return currentPoint; }

    public void setCurrentPoint(Point point) { currentPoint = point;}

    public int getTurn() { return nTurn; }

    private void updateStatus(Point newPoint) {
        board.setCellColour(newPoint, currentColour);
        currentColour = currentColour.getOppositeColor();
        nTurn++;
    }

    public boolean newMove(Point point){
        setCurrentPoint(point);
        if (Rulebook.queryRule(this, ValidPositionRule::new)) {
            updateStatus(point);
            return true;
        }
        return false;
    }

    public void performPieRule() {
        playerOne.changeSide();
        playerTwo.changeSide();
        nTurn++;
    }

    public void performPassRule(){
        currentColour = currentColour.getOppositeColor();
        nTurn++;
    }

}
