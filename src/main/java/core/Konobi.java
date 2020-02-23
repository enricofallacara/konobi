package core;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.Console.ConsoleMessageWriter;
import UI.InputHandler;
import UI.MessageWriter;
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


public class Konobi<T extends InputHandler> {

    private final StatusSupervisor supervisor;
    private final MessageWriter messageWriter;
    private final T inputHandler;

    public Konobi(int size, MessageWriter mw, T ih) {
        supervisor = new StatusSupervisor(size);
        messageWriter = mw;
        inputHandler = ih;

    }

    public boolean checkAndPerformPassRule() {
        if (Rulebook.queryRule(supervisor, PassRule::new)) {
            supervisor.performPassRule();
            messageWriter.notifyPass();
            return true;
        }
        return false;
    }

    public boolean checkAndPerformPieRule() {
        if (Rulebook.queryRule(supervisor, PieRule::new) && inputHandler.askPieRule()) {
            supervisor.performPieRule();
            messageWriter.notifyPieRule();
            return true;
        }
        return false;
    }

    public boolean checkAndPerformNewMove(Point point) {
        if (!supervisor.newMove(point)) {
            messageWriter.notifyInvalidMove();
            return false;
        }
        return true;
    }

    public boolean checkAndPerformEndGameRule() {
       if (Rulebook.queryRule(supervisor, EndGameRule::new)) {
           Player winner = supervisor.getLastPlayer();
           messageWriter.notifyEndGame(winner);
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
