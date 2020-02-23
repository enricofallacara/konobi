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

import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
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

    public boolean checkAndPerformNewMove(Runnable notifier, Point point) {
        if (!supervisor.newMove(point)) {
            notifier.run();
            return false;
        }
        return true;
    }

    public boolean checkAndPerformEndGameRule(Consumer<Player> consumer) {
       if (Rulebook.queryRule(supervisor, EndGameRule::new)) {
           Player winner = supervisor.getLastPlayer();
           consumer.accept(winner);
           return true;
       }
       return false;
    }

    public Player getCurrentPlayer() {
        return supervisor.getCurrentPlayer();
    }

    public Player getLastPlayer() {
        return supervisor.getLastPlayer();
    }

    public void play() {
        do {
            playTurn();
        } while(!checkAndPerformEndGameRule(ConsoleMessageWriter::notifyEndGame));

        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
    }

    private void playTurn() {
        ConsoleBoardWriter.displayBoard(supervisor.getBoard());
        if (checkAndPerformPassRule(ConsoleMessageWriter::notifyPass)) {
            return;
        }
        if (checkAndPerformPieRule(ConsoleMessageWriter::notifyPieRule, ConsoleInputHandler::askPieRule)) {
            return;
        }
        while (true) {
            if (checkAndPerformNewMove(ConsoleMessageWriter::notifyInvalidMove, ConsoleInputHandler.getInput(supervisor)))
                break;
        }
    }

}
