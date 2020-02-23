package UI.GUI;

import UI.MessageWriter;
import UI.Messages;
import core.Entities.Player;
import javafx.scene.control.Alert;


public class GUIMessageWriter implements MessageWriter {

    private static void createAndSetAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void notifyPass() {
        createAndSetAlert("Pass Rule Information", Messages.pass);
    }

    public void notifyEndGame(Player player) {
        createAndSetAlert("End Game Information", String.format(Messages.endGame, player.getName(), player.getColour()));
    }

    public void notifyInvalidMove() {
        createAndSetAlert("Invalid Move Information", Messages.invalidMove);
    }

    public void notifyPieRule() {
        createAndSetAlert("Pie Rule Information", Messages.pieRule);
    }

}
