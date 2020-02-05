package user_interface;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.Point;

public class GUIMouseInputHandler implements EventHandler<MouseEvent> {
    private GUI gui;
    // TODO: con questo design, sarebbe bello rimuovere la duplicazione sul costruttore e il
    // membro privato degli Handler, ma la classe astratta non fa una bellissima figura
    // TODO: si potrebbe anche aggiungere un evento InvalidMove e un evento ValidMove che a
    // a sua volta scatena a cascata tutti gli eventi di "cambio turno", anche se sarebbe un poco
    // eccessivo
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
    }

    private void updateGUIAndFireEvents(int columnIndex, int rowIndex) {
        gui.getBoardFiller().addPiece(gui.getGridBoard(), columnIndex, rowIndex, gui.getSupervisor().getLastPlayer());
        gui.getBoardFiller().switchLabelsCurrentPlayer(gui.getLabelBoard());

        gui.getGridBoard().fireEvent(new EndGameEvent());
        gui.getGridBoard().fireEvent(new PassRuleEvent());
        gui.getGridBoard().fireEvent(new PieRuleEvent());

    }
}
