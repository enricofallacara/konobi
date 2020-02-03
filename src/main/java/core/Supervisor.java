package core;

import java.awt.*;

public class Supervisor {
    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    // private final ArrayList<Point> moves;
    private Point currentPoint;
    private Color currentColor;
    private int nTurn;

    public Supervisor(int boardSize) {
        board = new Board(boardSize);
        playerOne = new Player(Color.black, "playerOne");
        playerTwo = new Player(Color.white, "playerTwo");
        currentColor = Color.black;
        nTurn = 1;
        // moves = new ArrayList<>();
    }

    public Player getCurrentPlayer() { return (playerOne.getColor() == currentColor) ? playerOne : playerTwo; }

    public Player getLastPlayer() { return (playerOne.getColor() == currentColor) ? playerTwo : playerOne; }

    public Point getCurrentPoint() { return currentPoint; }

    public void setCurrentPoint(Point point) { currentPoint = point;}

    public int getTurn() { return nTurn; }

    private void updateStatus(Point newPoint) {
        // moves.add(newPoint);
        board.setCell(newPoint, currentColor);
        currentColor = currentColor.getOppositeColor();
        nTurn++;
    }

    public boolean newMove(Point point){
        setCurrentPoint(point);

        // TODO: le mosse invalide (sopra una pedina o fuori dalla board)
        //       sarebbero più propriamente eccezioni?

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

    public Board getBoard() { return board; }

}
