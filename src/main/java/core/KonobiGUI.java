package core;

import UI.Console.ConsoleBoardWriter;
import UI.Console.ConsoleInputHandler;
import UI.GUI.GUIInputHandler;
import UI.GUI.GUIMessageWriter;
import UI.InputHandler;
import UI.MessageWriter;
import core.Entities.Player;
import core.Entities.Rulebook;
import core.Entities.StatusSupervisor;
import core.Rules.EndGameRule;
import core.Rules.PassRule;
import core.Rules.PieRule;
import java.awt.Point;


public class KonobiGUI extends AbstractKonobi<GUIMessageWriter, GUIInputHandler> {


    public KonobiGUI(int size, GUIMessageWriter mw, GUIInputHandler ih) {
        super(size, mw, ih);
    }

    public Player getCurrentPlayer() { return supervisor.getCurrentPlayer(); }

    public Player getLastPlayer() { return supervisor.getLastPlayer(); }

}
