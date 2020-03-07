package UI.Console;

import UI.Console.Exceptions.InputCoordinatesException;
import UI.Console.Exceptions.InputPieRuleException;
import UI.Console.Exceptions.InputSizeException;
import UI.InputHandler;
import UI.Messages;
import core.Entities.StatusSupervisor;
import java.awt.Point;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleInputHandler implements InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public boolean askPieRule() {
        ConsoleMessageWriter.pieRuleAskMessage();
        String answer;
        try {
            answer = scanner.next();
            if(!(answer.equals("y") || answer.equals("n")))
                throw new InputPieRuleException(Messages.INVALID_STRING_INPUT);
        } catch(InputPieRuleException e) {
            System.out.println(e.getMessage());
            scanner.next();
            return askPieRule();
        }
        return answer.toLowerCase().equals("y");
    }

    private static int getInteger() {
        try {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            else
                throw new InputMismatchException();
            }
            catch (InputMismatchException e) {
                System.out.println(Messages.INVALID_INTEGER_INPUT);
                scanner.next();
                return getInteger();
            }
    }

    public int askSize() {
        System.out.println(Messages.ASK_SIZE);
        int size;
        try {
            size = getInteger();
            if (size < 3 || size > 11)
                throw new InputSizeException(Messages.INVALID_SIZE_INPUT);

        } catch(InputSizeException e) {
            System.out.println(e.getMessage());
            return askSize();
        }
        return size;
    }

    public static Point getInput(StatusSupervisor supervisor) {
        ConsoleMessageWriter.displayPlayer(supervisor.getCurrentPlayer());
        ConsoleMessageWriter.getXInputMessage();
        int newX = getInteger();
        ConsoleMessageWriter.getYInputMessage();
        int newY = getInteger();
        Point point = new Point(newX,newY);

        try {
            if(!supervisor.getBoard().isOnBoard(point))
                throw new InputCoordinatesException(Messages.INVALID_COORDINATES_INPUT);

        } catch(InputCoordinatesException e) {
            System.out.println(e.getMessage());
            return getInput(supervisor);
        }
        return point;
    }

}
