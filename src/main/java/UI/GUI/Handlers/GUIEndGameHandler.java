package UI.GUI.Handlers;

import UI.GUI.Events.EndGameEvent;
import UI.GUI.GUI;
import javafx.event.EventHandler;


public class GUIEndGameHandler implements EventHandler<EndGameEvent> {

    private final GUI gui;

    public GUIEndGameHandler(GUI g) { gui = g; }

    @Override
    public void handle(EndGameEvent event) {
        if (gui.getGame().checkAndPerformEndGameRule()) {
            gui.stop();
        }
        event.consume();
    }

}
