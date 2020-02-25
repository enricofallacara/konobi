package UI.GUI.Handlers;

import UI.GUI.EventsFactory;
import UI.GUI.GUI;
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

        if (!gui.getGame().checkAndPerformNewMove(new Point(columnIndex, rowIndex))) {
            return;
        }
        updateGUIAndFireEvents(columnIndex, rowIndex);
        event.consume();
    }

    private void updateGUIAndFireEvents(int columnIndex, int rowIndex) {
        gui.getBoardFiller().addPiece(gui.getGridBoard(), columnIndex, rowIndex, gui.getGame().getLastPlayerColour());
        gui.getBoardFiller().switchLabelsCurrentPlayer(gui.getLabelBoard());
        EventsFactory.create().forEach(x -> gui.getGridBoard().fireEvent(x));
    }

}
