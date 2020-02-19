package UI.GUI;

import UI.Messages;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


public class GUIAsker {

    public static boolean askPieRule() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pie Rule Dialog");
        alert.setContentText(Messages.pieRuleQuery);
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
    }

}
