package core;

import UI.InputHandler;
import UI.MessageWriter;
import core.Entities.Rulebook;
import core.Entities.StatusSupervisor;
import core.Rules.EndGameRule;
import core.Rules.PassRule;
import core.Rules.PieRule;
import java.awt.Point;


public abstract class AbstractKonobi<T extends MessageWriter, V extends InputHandler> {

    protected final StatusSupervisor supervisor;
    protected final T messageWriter;
    protected final V inputHandler;

    public AbstractKonobi(int size, T mw, V ih) {
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
            messageWriter.notifyEndGame(supervisor.getLastPlayer());
            return true;
        }
        return false;
    }

}
