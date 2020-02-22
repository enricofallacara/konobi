package core.Entities;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import core.Rules.EndGameRule;
import core.Rules.PassRule;
import core.Rules.PieRule;


public class Konobi {

    private final StatusSupervisor supervisor;

    public Konobi(int size) {
        supervisor = new StatusSupervisor(size);
    }

    public void play() {
        do {
            playTurn();
        } while(!Rulebook.queryRule(supervisor, EndGameRule::new));

        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
        Player winner = supervisor.getLastPlayer();
        ConsoleMessageWriter.notifyEndGame(winner);
    }

    private void playTurn() {
        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            supervisor.performPassRule();
            ConsoleMessageWriter.notifyPass();
            return;
        }
        if (Rulebook.queryRule(supervisor, PieRule::new) && ConsoleInputHandler.askPieRule()) {
            supervisor.performPieRule();
            ConsoleMessageWriter.notifyPieRule();
            return;
        }
        while (!supervisor.newMove(ConsoleInputHandler.getInput(supervisor))) {
            ConsoleMessageWriter.notifyInvalidMove();
        }
    }

}
