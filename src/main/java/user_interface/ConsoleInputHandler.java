package user_interface;

import core.Player;

import java.awt.*;
import java.util.Scanner;

import static user_interface.ConsoleMessageWriter.displayPlayer;

public class ConsoleInputHandler {

    private final Scanner scanner;

    public ConsoleInputHandler() { scanner = new Scanner(System.in); }

    public boolean askPieRule() {
        ConsoleMessageWriter.pieRuleMessage();
        String answer = scanner.next();
        // TODO: e se scrive Y?
        return answer.equals("y");
    }

    public int checkInput(String message){
        System.out.println(message);
        while(!scanner.hasNextInt()){
            System.out.println("Invalid input, please insert a valid integer!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public int askSize() {

        int size;

        do{
            size = checkInput("Enter the Size of the Board (the grid must be, at least, 3x3!)");
        }while( size <= 2);

        return size;
    }

    public Point getInput(Player player) {
        displayPlayer(player);
        int newX = checkInput("Please Insert Next X Coordinate: ");
        int newY = checkInput("Please Insert Next Y Coordinate: ");
        return new Point(newX, newY);
    }
}
