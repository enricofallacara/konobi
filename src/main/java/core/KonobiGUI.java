package core;

import UI.GUI.GUIInputHandler;
import UI.GUI.GUIMessageWriter;
import core.Entities.Colour;


public class KonobiGUI extends AbstractKonobi<GUIMessageWriter, GUIInputHandler> {

    public KonobiGUI(int size, GUIMessageWriter mw, GUIInputHandler ih) {
        super(size, mw, ih);
    }

    //TODO: Vedere se implementare metodo play come accade in KonobiConsole

    public String getCurrentPlayerName() { return supervisor.getCurrentPlayer().getName(); }

    public String getLastPlayerName() { return supervisor.getLastPlayer().getName(); }

    public Colour getLastPlayerColour() { return supervisor.getLastPlayer().getColour(); }

}
