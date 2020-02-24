package core;

import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;


public class Main {


    public static void main(String[] args) {
        ConsoleMessageWriter.printLogo();
        ConsoleMessageWriter.showInstructions();
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        int size = inputHandler.askSize();
        KonobiConsole game = new KonobiConsole(size, new ConsoleMessageWriter(), inputHandler);
        game.play();
    }

}
