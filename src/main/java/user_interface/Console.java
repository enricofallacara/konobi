package user_interface;

import core.Board;
import core.Color;
import core.Player;

import java.awt.*;
import java.util.Scanner;

public class Console implements UserInterface {
    private Scanner scanner;

    public Console(){
        scanner = new Scanner(System.in);
    }

    @Override
    public Point getInput() {
        System.out.println("Please Insert Next X Coordinate: ");
        int newX = scanner.nextInt();
        System.out.println("Please Insert Next Y Coordinate: ");
        int newY = scanner.nextInt();
        return new Point(newX, newY);
    }

    @Override
    public boolean askPieRule() {
        System.out.println("Do You Want to Apply the Pie Rule? (y/n)");
        String answer = scanner.next();
        return answer.equals("y");
    }

    @Override
    public void notifyEndGame(Player player) {

    }

    @Override
    public int askSize() {

        return 11;
    }

    @Override
    public void notifyPass() {
        System.out.println("YOU SHALL PASS!");
    }

    @Override
    public void display(Board board){
        for(int y = board.getSize() - 1 ; y >= 0; y--){
            String space = (y < 10) ? "  " : " ";
            System.out.print(y + space);
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
        System.out.print("   ");
        for (int i = 0; i < board.getSize(); ++i) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    @Override
    public void notifyInvalidMove() { System.out.println("The Selected Position is Invalid!"); }
}
