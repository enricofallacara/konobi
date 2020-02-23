package core;

import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import UI.InputHandler;

public class Main {


    public static void main(String[] args) {
        ConsoleMessageWriter.printLogo();
        ConsoleMessageWriter.showInstructions();
        InputHandler inputHandler = new ConsoleInputHandler();
        int size = inputHandler.askSize();
        Konobi<InputHandler> game = new Konobi<>(size, new ConsoleMessageWriter(), inputHandler);
        game.play();
    }

}
