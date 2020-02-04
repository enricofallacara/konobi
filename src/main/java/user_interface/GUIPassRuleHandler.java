package user_interface;

import core.PassRule;
import core.Rulebook;
import javafx.event.EventHandler;

public class GUIPassRuleHandler implements EventHandler<PassRuleEvent> {
    private GUI gui;

    public GUIPassRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PassRuleEvent event) {
        if (Rulebook.queryRule(gui.getSupervisor(), PassRule::new) ){
            gui.getSupervisor().performPassRule();
            GUIMessageWriter.notifyPass();
        }
    }
}
