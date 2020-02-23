package UI.GUI.Handlers;

import UI.GUI.EventsFactory;
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
        System.out.println("column: " + columnIndex + " row: " + rowIndex);

        if (!gui.getGame().checkAndPerformNewMove(GUIMessageWriter::notifyInvalidMove,
                new Point(columnIndex, rowIndex))) {
            return;
        }
        updateGUIAndFireEvents(columnIndex, rowIndex);
        event.consume();
    }

    private void updateGUIAndFireEvents(int columnIndex, int rowIndex) {
        gui.getBoardFiller().addPiece(gui.getGridBoard(), columnIndex, rowIndex, gui.getGame().getLastPlayer().getColour());
        gui.getBoardFiller().switchLabelsCurrentPlayer(gui.getLabelBoard());
        EventsFactory.createEvents().forEach(x -> gui.getGridBoard().fireEvent(x));
    }

}
