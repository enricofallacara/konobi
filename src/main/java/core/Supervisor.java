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

    private void updateStatus(Point newPoint, Player currentPlayer) {
        // moves.add(newPoint);
        board.setCell(newPoint, currentColor);
        // TODO: vedere che il colore corrente è l'opposto del colore
        //       del player corrente fa un poco male all'anima.
        currentColor = currentPlayer.getOppositeColor();
        nTurn++;
    }

    public boolean newMove(Point point){
        Player currentPlayer = getCurrentPlayer();
        setCurrentPoint(point);

        // TODO: le mosse invalide (sopra una pedina o fuori dalla board)
        //       sarebbero più propriamente eccezioni?

        if (Rulebook.queryRule(this, ValidPositionRule::new)) {
            updateStatus(point, currentPlayer);
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

    /*public boolean isEndGame() { return Rulebook.queryEndGameRule(this); }

    public boolean isPassRule() { return Rulebook.queryPassRule(this); }

    public boolean isPieRule() { return Rulebook.queryPieRule(this); }*/
}
