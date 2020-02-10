package Core.Entities;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import Core.Rules.EndGameRule;
import Core.Rules.PassRule;
import Core.Rules.PieRule;

public class Konobi {
    private final Supervisor supervisor;
    private final ConsoleBoardWriter boardDisplayer = new ConsoleBoardWriter();
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    public Konobi() {
        ConsoleMessageWriter.printLogo();
        supervisor = new Supervisor(inputHandler.askSize());
        ConsoleMessageWriter.showInstructions(supervisor);
    }

    public void play() {
        do {
            playTurn();
        } while(!Rulebook.queryRule(supervisor, EndGameRule::new));

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
            ConsoleMessageWriter.notifyPieRule();
            return;
        }
        while (!supervisor.newMove(inputHandler.getInput(supervisor))) {
            ConsoleMessageWriter.notifyInvalidMove();
        }
    }

}
