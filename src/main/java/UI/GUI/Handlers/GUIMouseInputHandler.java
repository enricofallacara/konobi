package UI.GUI.Handlers;

import UI.GUI.Events.EndGameEvent;
import UI.GUI.Events.PassRuleEvent;
import UI.GUI.Events.PieRuleEvent;
import UI.GUI.GUI;
import UI.GUI.GUIMessageWriter;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.awt.Point;


public class GUIMouseInputHandler implements EventHandler<MouseEvent> {

    private final GUI gui;

    public GUIMouseInputHandler(GUI g) { gui = g; }

    @Override
    public void handle(MouseEvent event) {
        int columnIndex = gui.coordinateConversion(event.getX());
        int rowIndex = gui.coordinateConversion(event.getY());

        if(!gui.getSupervisor().newMove(new Point(columnIndex, rowIndex))) {
            GUIMessageWriter.notifyInvalidMove();
            return;
        }
        updateGUIAndFireEvents(columnIndex, rowIndex);
        event.consume();
    }

    private void updateGUIAndFireEvents(int columnIndex, int rowIndex) {
        gui.getBoardFiller().addPiece(gui.getGridBoard(), columnIndex, rowIndex, gui.getSupervisor().getLastPlayer());
        gui.getBoardFiller().switchLabelsCurrentPlayer(gui.getLabelBoard());

        gui.getGridBoard().fireEvent(new EndGameEvent());
        gui.getGridBoard().fireEvent(new PassRuleEvent());
        gui.getGridBoard().fireEvent(new PieRuleEvent());

    }

}
