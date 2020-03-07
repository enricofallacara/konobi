package UI.Console;

import UI.MessageWriter;
import UI.Messages;
import core.Entities.Colour;
import core.Entities.Player;


public class ConsoleMessageWriter implements MessageWriter {

    public static void showInstructions() {
        System.out.println(String.format(Messages.INSTRUCTIONS,
                ConsoleCellRepresentation.getRepresentation(Colour.BLACK),
                ConsoleCellRepresentation.getRepresentation(Colour.WHITE)));
    }

    public void notifyInvalidMove() { System.out.println(Messages.INVALID_MOVE); }

    public static void printLogo() { System.out.println(Messages.CONSOLE_LOGO); }

    public static void displayPlayer(Player player) {
        System.out.println(String.format(Messages.PLAYER_TURN, player.getName(),
                ConsoleCellRepresentation.getRepresentation(player.getColour())));
    }

    public void notifyEndGame(Player player) {
        System.out.println(String.format(Messages.END_GAME, player.getName(),
                ConsoleCellRepresentation.getRepresentation(player.getColour())));
    }

    public void notifyPass() { System.out.println(Messages.PASS); }

    public static void pieRuleAskMessage() { System.out.println(Messages.PIE_RULE_QUERY); }

    public void notifyPieRule() { System.out.println(Messages.PIE_RULE); }

    public static void getXInputMessage() { System.out.println(Messages.X_INPUT); }

    public static void getYInputMessage() { System.out.println(Messages.Y_INPUT); }

}
