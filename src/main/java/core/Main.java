package core;


import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import core.Entities.Konobi;

public class Main {


    public static void main(String[] args) {
        ConsoleMessageWriter.printLogo();
        ConsoleMessageWriter.showInstructions();
        Konobi game = new Konobi(ConsoleInputHandler.askSize());
        game.play();
    }

}
