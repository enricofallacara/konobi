package UI.Console;

import UI.MessageWriter;
import UI.Messages;
import core.Entities.Colour;
import core.Entities.Player;


public class ConsoleMessageWriter implements MessageWriter {

    public static void showInstructions() {
        System.out.println(String.format(Messages.instructions,
                ConsoleCellRepresentation.getRepresentation(Colour.BLACK),
                ConsoleCellRepresentation.getRepresentation(Colour.WHITE)));
    }

    public void notifyInvalidMove() { System.out.println(Messages.invalidMove); }

    public static void printLogo() { System.out.println(Messages.consoleLogo); }

    public static void displayPlayer(Player player) {
        System.out.println(String.format(Messages.playerTurn, player.getName(),
                ConsoleCellRepresentation.getRepresentation(player.getColour())));
    }

    public void notifyEndGame(Player player) {
        System.out.println(String.format(Messages.endGame, player.getName(),
                ConsoleCellRepresentation.getRepresentation(player.getColour())));
    }

    public void notifyPass() { System.out.println(Messages.pass); }

    public static void pieRuleAskMessage() { System.out.println(Messages.pieRuleQuery); }

    public void notifyPieRule() { System.out.println(Messages.pieRule); }

    public static void getXInputMessage() { System.out.println(Messages.xInput); }

    public static void getYInputMessage() { System.out.println(Messages.yInput); }

}
