package core;

import java.awt.*;

public class Supervisor {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Point move;

    public Supervisor(int s){
        board = new Board(s);
        playerOne = new Player(Color.black);
        playerTwo = new Player(Color.white);
    }

    public void setCurrentMove(Point p){ move = p;}

    public  void  newMove(Point p){
        setCurrentMove(p);
        // TODO: check validity of the move
    }

    public static void query(){
        // CICLO WHILE DEL GIOCO
    }

    public static void startGame(){
       // TODO: pensare come passare la prima mossa
    }




}
