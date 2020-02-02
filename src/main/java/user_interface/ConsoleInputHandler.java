package user_interface;

import java.util.Scanner;

public class ConsoleInputHandler {

    private final Scanner scanner;

    public ConsoleInputHandler() { scanner = new Scanner(System.in); }

    public boolean askPieRule() {
        ConsoleMessageWriter.pieRuleMessage();
        String answer = scanner.next();
        // TODO: e se scrive Y?
        return answer.equals("y");
    }
}
