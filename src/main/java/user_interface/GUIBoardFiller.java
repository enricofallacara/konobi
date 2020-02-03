package user_interface;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUIBoardFiller {

    int boardSize;
    int tileSize;

    GUIBoardFiller(int bS, int tS) {
        boardSize = bS;
        tileSize = tS;
    }

    public GridPane createAndFillBoard() {

        GridPane gridPane = new GridPane();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Rectangle r = new Rectangle(col * tileSize,row * tileSize , tileSize, tileSize);
                r.setFill( (col + row) % 2 == 0 ? Color.PALEVIOLETRED : Color.DARKSEAGREEN);
                gridPane.addRow(row,r);
            }
        }
        /*for (int x = 0 ; x < size ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / (size));
            cc.setFillWidth(true);
            gridPane.getColumnConstraints().add(cc);
        }

        // row constraints:
        for (int y = 0 ; y < size; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / (size));
            rc.setFillHeight(true);
            gridPane.getRowConstraints().add(rc);
        }*/

        return gridPane;
    }
}
