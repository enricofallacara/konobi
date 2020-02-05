package user_interface;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11));
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(11, sizes);

        dialog.setTitle("Enter Size");
        dialog.setHeaderText(null);
        dialog.setContentText(Messages.askSize);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

        Optional<Integer> result = dialog.showAndWait();
        return result.orElse(11);

        /*
        int size = 11;
        TextInputDialog dialog = new TextInputDialog(Integer.toString(size));
        dialog.setTitle("Enter Size");
        dialog.setHeaderText(null);
        dialog.setContentText(Messages.askSize);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return Integer.parseInt(result.get());
        }
        Platform.exit();
        return 0;
         */
    }
}
