package user_interface;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class GUIAsker {

    public static boolean askPieRule() {
        // TODO: aggiungere delay
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pie Rule Dialog");
        alert.setContentText(Messages.pieRule);
        alert.setHeaderText(null);

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();

    }


    public static int askSize() {
        int size = 11;
        TextInputDialog dialog = new TextInputDialog(Integer.toString(size));
        dialog.setTitle("Enter Size");
        dialog.setHeaderText(null);
        //TODO: impedire di mettere < 3 come size.
        dialog.setContentText(Messages.askSize);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return Integer.parseInt(result.get());
        }
        Platform.exit();
        return 0;
    }
}
