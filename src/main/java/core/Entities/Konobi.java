package core.Entities;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import core.Rules.EndGameRule;
import core.Rules.PassRule;
import core.Rules.PieRule;

import java.util.concurrent.Callable;
import java.util.function.Supplier;


public class Konobi {

    private final StatusSupervisor supervisor;

    public Konobi(int size) {
        supervisor = new StatusSupervisor(size);
    }

    public boolean checkAndPerformPassRule(Runnable notifier) {
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            supervisor.performPassRule();
            notifier.run();
            return true;
        }
        return false;
    }

    public boolean checkAndPerformPieRule(Runnable notifier, Supplier<Boolean> asker) {
        if (Rulebook.queryRule(supervisor, PieRule::new) && asker.get()) {
            supervisor.performPieRule();
            notifier.run();
            return true;
        }
        return false;
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
        if (checkAndPerformPassRule(ConsoleMessageWriter::notifyPass)) {
            return;
        }
        if (checkAndPerformPieRule(ConsoleMessageWriter::notifyPieRule, ConsoleInputHandler::askPieRule)) {
            return;
        }
        while (!supervisor.newMove(ConsoleInputHandler.getInput(supervisor))) {
            ConsoleMessageWriter.notifyInvalidMove();
        }
    }

}
