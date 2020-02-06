package UI.GUI.Handlers;

import UI.GUI.Events.PieRuleEvent;
import UI.GUI.GUI;
import UI.GUI.GUIAsker;
import UI.GUI.GUIMessageWriter;
import core.Rules.PieRule;
import core.Entities.Rulebook;
import javafx.event.EventHandler;

public class GUIPieRuleHandler implements EventHandler<PieRuleEvent> {
    private GUI gui;

    public GUIPieRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PieRuleEvent event) {
        if (Rulebook.queryRule(gui.getSupervisor(), PieRule::new) && GUIAsker.askPieRule()){
            gui.getSupervisor().performPieRule();
            gui.getBoardFiller().switchLabelsColors(gui.getLabelBoard());
            GUIMessageWriter.notifyPieRule();
        }
    }
}
