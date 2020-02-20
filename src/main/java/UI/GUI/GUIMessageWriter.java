package UI.GUI;

import UI.Messages;
import core.Entities.Player;
import javafx.scene.control.Alert;


public class GUIMessageWriter {

    private static void createAndSetAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void notifyPass() {
        createAndSetAlert("Pass Rule Information", Messages.pass);
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pass Rule Information");
        alert.setHeaderText(null);
        alert.setContentText(Messages.pass);
        alert.showAndWait();*/
    }

    public static void notifyEndGame(Player player) {
        createAndSetAlert("End Game Information", String.format(Messages.endGame, player.getName(), player.getColour()));
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game Information");
        alert.setHeaderText(null);
        alert.setContentText(String.format(Messages.endGame, player.getName(), player.getColour()));
        alert.showAndWait();*/
    }

    public static void notifyInvalidMove() {
        createAndSetAlert("Invalid Move Information", Messages.invalidMove);
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Move Information");
        alert.setHeaderText(null);
        alert.setContentText(Messages.invalidMove);
        alert.showAndWait();*/
    }

    public static void notifyPieRule() {
        createAndSetAlert("Pie Rule Information", Messages.pieRule);
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pie Rule Information");
        alert.setHeaderText(null);
        alert.setContentText(Messages.pieRule);
        alert.showAndWait();*/
    }

}
