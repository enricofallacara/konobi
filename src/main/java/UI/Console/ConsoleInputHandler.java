package UI.Console;

import UI.Messages;
import core.Entities.Player;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputHandler {

    private final Scanner scanner;

    public ConsoleInputHandler() { scanner = new Scanner(System.in); }

    public boolean askPieRule() {
        ConsoleMessageWriter.pieRuleAskMessage();
        String answer;
        try {
            answer = scanner.next();
            if(!(answer.equals("y") || answer.equals("n")))
                throw new InputPieRuleException(Messages.invalidStringInput);
        } catch(InputPieRuleException e){
            System.out.println(e.getMessage());
            scanner.next();
            return askPieRule();
        }
        return answer.toLowerCase().equals("y");
    }
    // TODO: controllare anche che gli interi passati siano nel range giusto, magari
    //  con relativa eccezione
    public int getInteger() {
        try {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            else
                throw new InputMismatchException();
            }
            catch (InputMismatchException e){
                System.out.println(Messages.invalidIntegerInput);
                scanner.next();
                return getInteger();
            }
    }

    public int askSize() {
        System.out.println(Messages.askSize);
        int size;
        try {
            size = getInteger();
            if(size < 3 || size > 11)
                throw new InputSizeException(Messages.invalidIntegerInput);

        } catch(InputSizeException e){
            System.out.println(e.getMessage());
            return askSize();
        }
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
