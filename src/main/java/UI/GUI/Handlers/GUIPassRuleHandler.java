package UI.GUI.Handlers;

import UI.GUI.Events.PassRuleEvent;
import UI.GUI.GUI;
import javafx.event.EventHandler;


public class GUIPassRuleHandler implements EventHandler<PassRuleEvent> {

    private final GUI gui;

    public GUIPassRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PassRuleEvent event) {
        gui.getGame().checkAndPerformPassRule();
        event.consume();
    }

}
