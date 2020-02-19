package core;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import core.Entities.Player;
import core.Entities.Rulebook;
import core.Entities.StatusSupervisor;
import core.Rules.EndGameRule;
import core.Rules.PassRule;
import core.Rules.PieRule;


public class Konobi {

    private final StatusSupervisor supervisor;
    private final ConsoleBoardWriter boardWriter = new ConsoleBoardWriter();
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    public Konobi() {
        ConsoleMessageWriter.printLogo();
        supervisor = new StatusSupervisor(inputHandler.askSize());
        ConsoleMessageWriter.showInstructions(supervisor);
    }

    public void play() {
        do {
            playTurn();
        } while(!Rulebook.queryRule(supervisor, EndGameRule::new));

        boardWriter.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        ConsoleMessageWriter.notifyEndGame(winner);
    }

    private void playTurn() {
        boardWriter.displayBoard(supervisor.getBoard());
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            supervisor.performPassRule();
            ConsoleMessageWriter.notifyPass();
            return;
        }
        if (Rulebook.queryRule(supervisor, PieRule::new) && inputHandler.askPieRule()) {
            supervisor.performPieRule();
            ConsoleMessageWriter.notifyPieRule();
            return;
        }
        while (!supervisor.newMove(inputHandler.getInput(supervisor))) {
            ConsoleMessageWriter.notifyInvalidMove();
        }
    }

}
