package UI.Console;

import UI.Messages;
import core.Entities.Player;
import java.awt.Point;
import java.util.Scanner;


public class ConsoleInputHandler {

    private final Scanner scanner;

    public ConsoleInputHandler() { scanner = new Scanner(System.in); }

    public boolean askPieRule() {
        // TODO: eccezione se risponde qualcosa di diverso y/n.
        ConsoleMessageWriter.pieRuleAskMessage();
        String answer = scanner.next();
        return answer.toLowerCase().equals("y");
    }

    public int getInteger(){
        //TODO: potrebbe essere trattata come eccezione?
        while(!scanner.hasNextInt()){
            System.out.println(Messages.invalidIntegerInput);
            scanner.next();
        }
        return scanner.nextInt();
    }

    public int askSize() {
        int size;

        do {
            System.out.println(Messages.askSize);
            size = getInteger();
        } while( size <= 2 || size > 11 );

        return size;
    }

    public Point getInput(Player player) {
        ConsoleMessageWriter.displayPlayer(player);
        ConsoleMessageWriter.getXInputMessage();
        int newX = getInteger();
        ConsoleMessageWriter.getYInputMessage();
        int newY = getInteger();
        return new Point(newX, newY);
    }
}
