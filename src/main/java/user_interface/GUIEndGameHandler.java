package user_interface;

import core.EndGameRule;
import core.Rulebook;
import javafx.event.EventHandler;

public class GUIEndGameHandler implements EventHandler<EndGameEvent> {
    private GUI gui;

    public GUIEndGameHandler(GUI g) { gui = g; }

    @Override
    public void handle(EndGameEvent event) {
        if(Rulebook.queryRule(gui.getSupervisor(), EndGameRule::new)){
            GUIMessageWriter.notifyEndGame(gui.getSupervisor().getLastPlayer());
            gui.stop();
        }
    }
}
