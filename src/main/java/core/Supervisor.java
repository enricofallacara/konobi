package core;

import java.awt.*;

public class Supervisor {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Point move;
    private int nTurn;
    private Color currentTurn;

    public Supervisor(int s){
        board = new Board(s);
        playerOne = new Player(Color.black);
        playerTwo = new Player(Color.white);
        currentTurn = Color.black;
    }

    public Player getCurrentPlayer() {
        return (playerOne.getColor() == currentTurn) ? playerOne : playerTwo;
    }

    private void setCurrentMove(Point point){
        move = point;
        board.setCell(move, currentTurn);
    }

    public boolean newMove(Point point){
        Player currentPlayer = getCurrentPlayer();
        if(Rulebook.queryValidPosition(point, board, currentPlayer)) {
            setCurrentMove(point);
            currentTurn = currentPlayer.getOppositeColor();
            return true;
        }
        return false;
    }

    public static void query(){
        // CICLO WHILE DEL GIOCO
    }

    public static void startGame(){
       // TODO: pensare come passare la prima mossa
    }




}
