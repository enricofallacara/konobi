package core;


import user_interface.ConsoleBoardDisplayer;
import user_interface.ConsoleInputHandler;
import user_interface.ConsoleMessageWriter;

public class Konobi {

    Supervisor supervisor;

    ConsoleBoardDisplayer boardDisplayer = new ConsoleBoardDisplayer();
    ConsoleMessageWriter messageWriter = new ConsoleMessageWriter();
    ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    Konobi() {
        // TODO: Forse ha senso dividere initalise() e askSize();
        messageWriter.printLogo();
        supervisor = new Supervisor(inputHandler.askSize());
    }

    public void play() {
        while(!supervisor.isEndGame()) {
            playTurn();
        }

        boardDisplayer.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        messageWriter.notifyEndGame(winner);
    }

    private void playTurn() {
        boardDisplayer.displayBoard(supervisor.getBoard());
        if (supervisor.isPassRule()) {
            messageWriter.notifyPass();
            return;
        }
        if (supervisor.isPieRule() && inputHandler.askPieRule()) {
            supervisor.performPieRule();
            return;
        }
        while (!supervisor.newMove(inputHandler.getInput(supervisor.getCurrentPlayer()))) {
            messageWriter.notifyInvalidMove();
        }
    }
}
