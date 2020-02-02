package user_interface;

import core.Player;

public class ConsoleMessageWriter {


    public void notifyInvalidMove() { System.out.println("The Selected Position is Invalid!"); }

    public void printLogo(){
        System.out.println(" _   __                  _     _ \n" +
                "| | / /                 | |   (_)\n" +
                "| |/ /  ___  _ __   ___ | |__  _ \n" +
                "|    \\ / _ \\| '_ \\ / _ \\| '_ \\| |\n" +
                "| |\\  \\ (_) | | | | (_) | |_) | |\n" +
                "\\_| \\_/\\___/|_| |_|\\___/|_.__/|_|\n" +
                "                                 ");
    }

    public static void displayPlayer(Player player){
        System.out.println(player.getName() + "'s turn! " +
                ConsoleCellRepresentation.getRepresentation(player.getColor()));
    }

    public void notifyEndGame(Player player) {
        System.out.println("Winner: " + player.getName());
        System.out.println("Color: " + player.getColor());
    }

    public void notifyPass() { System.out.println("YOU SHALL PASS!"); }

    public static void pieRuleMessage() {
        System.out.println("Player two, do you want to apply the Pie Rule? (y/n)");
    }
}
