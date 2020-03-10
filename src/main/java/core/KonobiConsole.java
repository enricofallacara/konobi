package core;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;


public class KonobiConsole extends AbstractKonobi<ConsoleMessageWriter, ConsoleInputHandler> {

    public KonobiConsole(int size, ConsoleMessageWriter mw, ConsoleInputHandler ih) {
        super(size, mw, ih);
    }

    public void play(){
        do {
            playTurn();
        } while(!checkAndPerformEndGameRule());
        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
    }

    private void playTurn() {
        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
        if (checkAndPerformPassRule()) {
            return;
        }
        if (checkAndPerformPieRule()) {
            return;
        }
        while (true) {
            if (checkAndPerformNewMove(ConsoleInputHandler.getInput(supervisor)))
                break;
        }
    }

}
