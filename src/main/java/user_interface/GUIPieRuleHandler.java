package user_interface;

import core.PassRule;
import core.Rulebook;
import javafx.event.EventHandler;

public class GUIPieRuleHandler implements EventHandler<PieRuleEvent> {
    private GUI gui;

    public GUIPieRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PieRuleEvent event) {
        if(Rulebook.queryRule(gui.getSupervisor(), PassRule::new) ){
            gui.getSupervisor().performPassRule();
            GUIMessageWriter.notifyPass();
        }
    }
}
