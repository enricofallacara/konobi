package UI.GUI;

import core.Entities.Player;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;


public class GUIBoardWriter {

    final int boardSize;
    final int tileSize;
    private static final Map<core.Entities.Color, Paint> colorPaintMap = new HashMap<>() {{
        put(core.Entities.Color.black, Color.BLACK);
        put(core.Entities.Color.white, Color.WHITE);
    }};

    GUIBoardWriter(int bS, int tS) {
        boardSize = bS;
        tileSize = tS;
    }

    public GridPane createEmptyBoard() {

        GridPane gridPane = new GridPane();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Rectangle r = new Rectangle(col * tileSize,row * tileSize , tileSize, tileSize);
                r.setFill( (col + row) % 2 == 0 ? Color.PALEVIOLETRED : Color.DARKSEAGREEN);
                r.setStroke(Color.GRAY);
                gridPane.addRow(row,r);
            }
        }

        return gridPane;
    }

    public void addPiece(GridPane gridPane, int X, int Y, Player player) {
        Circle piece = new Circle(X * tileSize, Y * tileSize, tileSize*0.4);
        piece.setFill(colorPaintMap.get(player.getColor()));
        GridPane.setHalignment(piece, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(piece, VPos.CENTER); // To align vertically in the cell
        gridPane.add(piece, X, Y);

    }
    // TODO: Long Method smell?
    // No, vedi come in GUI.java
    public GridPane createLabelPane() {
        GridPane gridLabels = new GridPane();
        gridLabels.setVgap(10);

        Text p1 = new Text("PlayerOne: ");
        Text p2 = new Text("PlayerTwo: ");
        Text currentPlayer = new Text("Now playing: ");

        gridLabels.add(p1, 0, 0);
        gridLabels.add(p2, 0, 1);
        gridLabels.add(currentPlayer, 0, 2);

        Circle wCircle = new Circle(tileSize * 0.1);
        Circle bCircle = new Circle(tileSize * 0.1);
        Circle cCurrent = new Circle(tileSize * 0.1);

        wCircle.setFill(Color.BLACK);
        bCircle.setFill(Color.WHITE);
        cCurrent.setFill(Color.BLACK);

        gridLabels.add(wCircle, 1, 0);
        gridLabels.add(bCircle, 1, 1);
        gridLabels.add(cCurrent, 1, 2);

        return gridLabels;
    }

    public void switchLabelsColors( GridPane labelBoard ){
        // The index 3 corresponds to the PlayerOne circle
        Circle cBlack = (Circle) labelBoard.getChildren().get(3);
        cBlack.setFill(Color.WHITE);
        // The index 4 corresponds to the PlayerTwo circle
        Circle cWhite = (Circle) labelBoard.getChildren().get(4);
        cWhite.setFill(Color.BLACK);
    }

    public void switchLabelsCurrentPlayer(GridPane labelBoard ){
        // The index 5 corresponds to the CurrentPlayer circle
        Circle cCircle = (Circle) labelBoard.getChildren().get(5);
       cCircle.setFill(cCircle.getFill() == Color.BLACK ? Color.WHITE : Color.BLACK);
    }

}
