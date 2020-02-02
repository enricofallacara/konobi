package core;

import user_interface.Console;
import user_interface.ConsoleBoardDisplayer;
import user_interface.UserInterface;

public class Konobi {

    UserInterface userInterface;
    Supervisor supervisor;

    ConsoleBoardDisplayer boardDisplayer = new ConsoleBoardDisplayer();

    Konobi() {
        userInterface = new Console();
        // TODO: Forse ha senso dividere initalise() e askSize();
        supervisor = new Supervisor(userInterface.initialize());
    }

    public void play() {
        while(!supervisor.isEndGame()) {
            playTurn();
        }

        boardDisplayer.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        userInterface.notifyEndGame(winner);
    }

    private void playTurn() {
        boardDisplayer.displayBoard(supervisor.getBoard());
        if (supervisor.isPassRule()) {
            userInterface.notifyPass();
            return;
        }
        if (supervisor.isPieRule() && userInterface.askPieRule()) {
            supervisor.performPieRule();
            return;
        }
        while (!supervisor.newMove(userInterface.getInput(supervisor.getCurrentPlayer()))) {
            userInterface.notifyInvalidMove();
        }
    }
}
