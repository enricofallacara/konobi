package core;

import java.awt.*;
import java.util.ArrayList;

public class Supervisor {
    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private final ArrayList<Point> moves;
    private Point currentPoint;
    private int nTurn;
    private Color currentColor;

    public Supervisor(int s){
        board = new Board(s);
        playerOne = new Player(Color.black, "playerOne");
        playerTwo = new Player(Color.white, "playerTwo");
        currentColor = Color.black;
        nTurn = 1;
        moves = new ArrayList<>();
    }

    public Player getCurrentPlayer() {
        return (playerOne.getColor() == currentColor) ? playerOne : playerTwo;
    }
    public Player getLastPlayer() { return (playerOne.getColor() == currentColor) ? playerTwo : playerOne; }
    public Point getCurrentPoint() { return currentPoint; }
    public void setCurrentPoint(Point point) { currentPoint = point;}
    public int getTurn() { return nTurn; }

    private void updateStatus(Point newPoint, Player currentPlayer){
        moves.add(newPoint);
        board.setCell(newPoint, currentColor);
        currentColor = currentPlayer.getOppositeColor();
        nTurn++;
    }

    public boolean newMove(Point point){

        Player currentPlayer = getCurrentPlayer();
        setCurrentPoint(point);
        if (Rulebook.queryValidPosition(this)) {
            updateStatus(point, currentPlayer);
            return true;
        }
        return false;
    }

    public void performPieRule(){
        playerOne.changeSide();
        playerTwo.changeSide();
        nTurn++;
    }

    public Board getBoard(){ return board; }

    public boolean isEndGame() { return Rulebook.queryEndGameRule(this); }

    public boolean isPassRule() { return Rulebook.queryPassRule(this); }

    public boolean isPieRule() { return Rulebook.queryPieRule(this); }
}
