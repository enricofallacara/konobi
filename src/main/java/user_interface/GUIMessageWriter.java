package user_interface;

import core.Player;
import javafx.scene.control.Alert;

public class GUIMessageWriter {
    public static void notifyPass() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pass Rule Information");
        alert.setHeaderText(null);
        alert.setContentText("You shall pass!");
        alert.showAndWait();
    }

    public static void notifyEndGame(Player player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game Information");
        alert.setHeaderText(null);
        alert.setContentText(player.getName() + " has won!");
        alert.showAndWait();
    }

    public static void notifyInvalidMove() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Move Information");
        alert.setHeaderText(null);
        alert.setContentText("Invalid move! Try again!");
        alert.showAndWait();
    }
}
