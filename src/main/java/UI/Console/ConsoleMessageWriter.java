package UI.Console;

import UI.Messages;
import core.Entities.Player;
import core.Entities.Supervisor;

public class ConsoleMessageWriter {

    public static void showInstructions(Supervisor supervisor) {
        System.out.println(String.format(Messages.instructions,
                ConsoleCellRepresentation.getRepresentation(supervisor.getCurrentPlayer().getColor()),
                ConsoleCellRepresentation.getRepresentation(supervisor.getLastPlayer().getColor())));
    }

    public static void notifyInvalidMove() { System.out.println(Messages.invalidMove); }

    public static void printLogo() { System.out.println(Messages.consoleLogo); }

    public static void displayPlayer(Player player) {
        System.out.println(String.format(Messages.playerTurn, player.getName(),
                ConsoleCellRepresentation.getRepresentation(player.getColor())));
    }

    public static void notifyEndGame(Player player) {
        System.out.println(String.format(Messages.endGame, player.getName(), player.getColor()));
    }

    public static void notifyPass() { System.out.println(Messages.pass); }

    public static void pieRuleMessage() { System.out.println(Messages.pieRule); }

    public static void getXInputMessage() { System.out.println(Messages.xInput); }

    public static void getYInputMessage() { System.out.println(Messages.yInput); }

}
