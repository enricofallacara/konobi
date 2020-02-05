package UI.Console;

import UI.Messages;
import core.Player;

public class ConsoleMessageWriter {

    //TODO: devono essere tutte static o nessuna?

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
