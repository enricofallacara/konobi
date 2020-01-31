package user_interface;

import core.Board;
import core.Cell;
import core.Color;
import core.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console implements UserInterface {
    private final Scanner scanner;
    private final Map<Color, String> cellColorMap = new HashMap<>() {{
        //put(Color.black, "[\u25A0]");
        //put(Color.white, "[\u25A1]");
        put(Color.black, "[x]");
        put(Color.white, "[o]");
        put(null, "[ ]");
    }};
    public Console() { scanner = new Scanner(System.in); }

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
        System.out.println("Winner: " + player.getName());
        System.out.println("Color: " + player.getColor());
    }

    @Override
    public int askSize() {
        System.out.println("Enter the Size of the Board: ");
        return scanner.nextInt();
    }

    @Override
    public void notifyPass() {
        System.out.println("YOU SHALL PASS!");
    }

    private void displayCell(Cell cell) {
        System.out.print(cellColorMap.get(cell.getColor()));
    }

    private void displayRow(Board board, int y) {
        System.out.print(y + "\t");
        for (int x = 0; x < board.getSize(); x++) {
            displayCell(board.getCell(new Point(x, y)));
        }
        System.out.println();
    }

    private String padLeft(String s) {
        return String.format("%" + 3 + "s", s);
    }

    @Override
    public void display(Board board){
        for(int y = board.getSize() - 1 ; y >= 0; y--){
            displayRow(board, y);
        }

        System.out.print("\t");
        for (int i = 0; i < board.getSize(); ++i) {
            System.out.print(padLeft(i + " "));
        }

        System.out.println();
    }

    @Override
    public void notifyInvalidMove() { System.out.println("The Selected Position is Invalid!"); }
}
