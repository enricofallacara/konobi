package user_interface;

import core.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.Point;

public class GUIInputHandler implements EventHandler<MouseEvent> {
    private GUI gui;

    public GUIInputHandler(GUI g) { gui = g; }

    @Override
    public void handle(MouseEvent event) {
        Supervisor supervisor = gui.getSupervisor();
        int columnIndex = gui.coordinateConversion(event.getX());
        int rowIndex = gui.coordinateConversion(event.getY());

        if(!supervisor.newMove(new Point(columnIndex, rowIndex))) {
            GUIMessageWriter.notifyInvalidMove();
            return;
        }

        gui.getBoardFiller().addPiece(gui.getGridBoard(), columnIndex, rowIndex, supervisor.getLastPlayer());
        gui.getGridBoard().fireEvent(new EndGameEvent());
        gui.getGridBoard().fireEvent(new PassRuleEvent());
        gui.getGridBoard().fireEvent(new PieRuleEvent());
    }
}
