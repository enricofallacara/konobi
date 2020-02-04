package user_interface;

import core.PieRule;
import core.Rulebook;
import javafx.event.EventHandler;

public class GUIPassRuleHandler implements EventHandler<PassRuleEvent> {
    private GUI gui;

    public GUIPassRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PassRuleEvent event) {
        if(Rulebook.queryRule(gui.getSupervisor(), PieRule::new) && GUIAsker.askPieRule()){
            gui.getSupervisor().performPieRule();
            gui.switchLabelsColors();
        }
    }
}
