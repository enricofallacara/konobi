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
        put(Color.black, "[\u25A0]");
        put(Color.white, "[\u25A1]");
        put(null, "[ ]");
    }};
    public Console() {
        scanner = new Scanner(System.in);
        initialize();
    }

    @Override
    public Point getInput(Player player) {
        displayPlayer(player);
        int newX = checkInput("Please Insert Next X Coordinate: ");
        int newY = checkInput("Please Insert Next Y Coordinate: ");
        return new Point(newX, newY);
    }

    @Override
    public boolean askPieRule() {
        System.out.println("PlayerTwo, do You Want to Apply the Pie Rule? (y/n)");
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

        int size;

        do{
            size = checkInput("Enter the Size of the Board (the grid must be, at least, 3x3!)");
        }while( size <= 2);

        return size;
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
        System.out.println("\f");
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

    @Override
    public void initialize(){
        printLogo();
    }

    public void printLogo(){
        System.out.println(" _   __                  _     _ \n" +
                           "| | / /                 | |   (_)\n" +
                           "| |/ /  ___  _ __   ___ | |__  _ \n" +
                           "|    \\ / _ \\| '_ \\ / _ \\| '_ \\| |\n" +
                           "| |\\  \\ (_) | | | | (_) | |_) | |\n" +
                           "\\_| \\_/\\___/|_| |_|\\___/|_.__/|_|\n" +
                "                                 ");
    }

    public void displayPlayer(Player player){
        System.out.println(player.getName() + "'s turn! " + cellColorMap.get(player.getColor()));
    }
    public int checkInput(String message){
        System.out.println(message);
        while(!scanner.hasNextInt()){
            System.out.println("Invalid input, please insert a valid integer!");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
