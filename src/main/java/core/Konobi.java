package core;


import user_interface.ConsoleBoardWriter;
import user_interface.ConsoleInputHandler;
import user_interface.ConsoleMessageWriter;

public class Konobi {

    //Test
    Supervisor supervisor;

    ConsoleBoardWriter boardDisplayer = new ConsoleBoardWriter();
    ConsoleMessageWriter messageWriter = new ConsoleMessageWriter();
    ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    public Konobi() {
        // TODO: Forse ha senso dividere initalise() e askSize();
        messageWriter.printLogo();
        supervisor = new Supervisor(inputHandler.askSize());
    }

    public void play() {
        while(!Rulebook.queryRule(supervisor, EndGameRule::new)) {
            playTurn();
        }

        boardDisplayer.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        messageWriter.notifyEndGame(winner);
    }

    private void playTurn() {
        boardDisplayer.displayBoard(supervisor.getBoard());
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            messageWriter.notifyPass();
            return;
        }
        if (Rulebook.queryRule(supervisor, PieRule::new) && inputHandler.askPieRule()) {
            supervisor.performPieRule();
            return;
        }
        while (!supervisor.newMove(inputHandler.getInput(supervisor.getCurrentPlayer()))) {
            messageWriter.notifyInvalidMove();
        }
    }
}
