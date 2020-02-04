package core;


import user_interface.ConsoleBoardWriter;
import user_interface.ConsoleInputHandler;
import user_interface.ConsoleMessageWriter;

public class Konobi {

    Supervisor supervisor;

    ConsoleBoardWriter boardDisplayer = new ConsoleBoardWriter();
    ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    public Konobi() {
        ConsoleMessageWriter.printLogo();
        supervisor = new Supervisor(inputHandler.askSize());
    }

    public void play() {
        do {
            playTurn();
        }while(!Rulebook.queryRule(supervisor, EndGameRule::new));

        boardDisplayer.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        ConsoleMessageWriter.notifyEndGame(winner);
    }

    private void playTurn() {
        boardDisplayer.displayBoard(supervisor.getBoard());
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            supervisor.performPassRule();
            ConsoleMessageWriter.notifyPass();
            return;
        }
        if (Rulebook.queryRule(supervisor, PieRule::new) && inputHandler.askPieRule()) {
            supervisor.performPieRule();
            return;
        }
        while (!supervisor.newMove(inputHandler.getInput(supervisor.getCurrentPlayer()))) {
            ConsoleMessageWriter.notifyInvalidMove();
        }
    }
}
