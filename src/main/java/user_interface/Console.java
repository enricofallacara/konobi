package user_interface;

import core.Board;
import core.Color;
import core.Player;

import java.awt.*;

public class Console implements UserInterface {

    public Console(){

    }
    @Override
    public Point getInput() {
        return null;
    }

    @Override
    public boolean askPieRule() {
        return false;
    }

    @Override
    public void endGame(Player player) {

    }

    public void display(Board board){
        for(int y = board.getSize() - 1 ; y >= 0; y--){
            for(int x = 0; x < board.getSize(); x++){
                if(board.getCell(new Point(x,y)).getColor() == Color.black)
                    System.out.print("[" + "x" + "]");
                else if(board.getCell(new Point(x,y)).getColor() == Color.white)
                    System.out.print("[" + "o" + "]");
                else
                    System.out.print("[" + " " + "]");
            }
            System.out.println();
        }
    }
}
