package user_interface;

import core.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.Point;

public class GUIInputHandler implements EventHandler<MouseEvent> {
    private GUI gui;

    public GUIInputHandler(GUI g) { gui = g; }
    // TODO: Long Method smell
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

        if(Rulebook.queryRule(supervisor, EndGameRule::new)){
            GUIMessageWriter.notifyEndGame(supervisor.getLastPlayer());
            gui.stop();
        }
        if(Rulebook.queryRule(supervisor, PieRule::new) && GUIAsker.askPieRule()){
            supervisor.performPieRule();
        }
        if(Rulebook.queryRule(supervisor, PassRule::new) ){
            supervisor.performPassRule();
            GUIMessageWriter.notifyPass();
        }
    }
}
