package UI.GUI.Handlers;

import UI.GUI.Events.PieRuleEvent;
import UI.GUI.GUI;
import javafx.event.EventHandler;


public class GUIPieRuleHandler implements EventHandler<PieRuleEvent> {

    private final GUI gui;

    public GUIPieRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PieRuleEvent event) {
        if (gui.getGame().checkAndPerformPieRule()) {
            gui.getBoardFiller().switchLabelsColors(gui.getLabelBoard());
        }
        event.consume();
    }

}
