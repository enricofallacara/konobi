package core;

import java.awt.*;
import java.util.ArrayList;

public class Supervisor {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Point> moves;
    private int nTurn;
    private Color currentColor;

    public Supervisor(int s){
        board = new Board(s);
        playerOne = new Player(Color.black);
        playerTwo = new Player(Color.white);
        currentColor = Color.black;
        nTurn = 0;
        moves = new ArrayList<>();
    }

    public Player getCurrentPlayer() {
        return (playerOne.getColor() == currentColor) ? playerOne : playerTwo;
    }

    private void updateStatus(Point newPoint, Player currentPlayer){
        moves.add(newPoint);
        board.setCell(newPoint, currentColor);
        currentColor = currentPlayer.getOppositeColor();
        nTurn++;
    }

    public boolean newMove(Point point){
        Player currentPlayer = getCurrentPlayer();
        if(Rulebook.queryValidPosition(point, board, currentPlayer)) {
            updateStatus(point, currentPlayer);
            return true;
        }
        return false;
    }

    public static boolean query() {
        /*
        if(ispassrule) {return true;}
        if(ispierule) {
            if(intercaccia.askpierule()) {
                performpierule;
                return true;
            }
        }

        newmove = intefaccia.input();
        newMove(newmove);

        if(winning) {iterface.win(), return false;};
        */
        return false;
    }

    public static void startGame(){
       // TODO: pensare come passare la prima mossa
    }




}
