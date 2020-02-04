package user_interface;

import core.Player;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUIBoardFiller {

    int boardSize;
    int tileSize;

    GUIBoardFiller(int bS, int tS) {
        boardSize = bS;
        tileSize = tS;
    }

    public GridPane createEmptyBoard() {

        GridPane gridPane = new GridPane();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Rectangle r = new Rectangle(col * tileSize,row * tileSize , tileSize, tileSize);
                r.setFill( (col + row) % 2 == 0 ? Color.PALEVIOLETRED : Color.DARKSEAGREEN);
                gridPane.addRow(row,r);
            }
        }

        return gridPane;
    }

    // TODO: ha senso metterla nella classe Cell o in una nuova classe?
    private static final Map<core.Color, Paint> colorPaintMap = new HashMap<>() {{
        put(core.Color.black, Color.BLACK);
        put(core.Color.white, Color.WHITE);
    }};

    public void addPiece(GridPane gridPane, int X, int Y, Player player) {
        Circle piece = new Circle(X * tileSize, Y * tileSize, tileSize*0.4);
        piece.setFill(colorPaintMap.get(player.getColor()));
        GridPane.setHalignment(piece, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(piece, VPos.CENTER); // To align vertically in the cell
        gridPane.add(piece, X, Y);

    }

    public GridPane createLabelPane() {
        // TODO: metodo per invertire i cerchietti colorati.
        GridPane gridLabels = new GridPane();
        gridLabels.setVgap(10);

        Text p1 = new Text("PlayerOne: ");
        Text p2 = new Text("PlayerTwo: ");

        gridLabels.add(p1, 0, 0);
        gridLabels.add(p2, 0, 1);

        Circle wCircle = new Circle(tileSize * 0.1);
        Circle bCircle = new Circle(tileSize * 0.1);

        wCircle.setFill(Color.BLACK);
        bCircle.setFill(Color.WHITE);

        gridLabels.add(wCircle, 1, 0);
        gridLabels.add(bCircle, 1, 1);

        return gridLabels;
    }
}
