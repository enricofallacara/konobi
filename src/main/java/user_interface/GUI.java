package user_interface;

import core.Board;
import core.Player;
import core.Supervisor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GUI extends Application implements UserInterface{
    private final int SIZE = 50;
    private Stage stage;
    private GridPane gridPane;
    Supervisor supervisor;


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        initialize();
    }

    public void initUI(int size) {

        gridPane = createAndFillBoard(size);
        supervisor = new Supervisor(size);

        gridPane.setOnMouseClicked(e -> {

            inputHandler(e.getX(), e.getY());

            /*
            Rectangle rect = new Rectangle(e.getX() * SIZE, e.getY() * SIZE, SIZE, SIZE);
            rect.setFill(Color.BLACK);
            int columIndex = (int)e.getX()/SIZE;
            int rowIndex = (int)e.getY()/SIZE;
            if (columIndex < size && rowIndex < size) {
                gridPane.add(rect, columIndex, rowIndex);
            }
             */
        });

        Scene scene = new Scene(gridPane, Color.WHITESMOKE);
        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }

    private int coordinateConversion(double coordinate) {
        return (int)coordinate/SIZE;
    }


    private static final Map<core.Color, Paint> colorPaintMap = new HashMap<>() {{
        put(core.Color.black, Color.BLACK);
        put(core.Color.white, Color.WHITE);
    }};


    private void inputHandler(double X, double Y) {
        int columIndex = coordinateConversion(X);
        int rowIndex = coordinateConversion(Y);
        if(!supervisor.newMove(new Point(columIndex, rowIndex))) {
            System.out.println("Invalid move");
            return;
        }

        Rectangle rect = new Rectangle(X * SIZE, Y * SIZE, SIZE-12, SIZE-12);

        rect.setFill(colorPaintMap.get(supervisor.getLastPlayer().getColor()));
        gridPane.add(rect, columIndex, rowIndex);

    }

    private GridPane createAndFillBoard(int size) {

        gridPane = new GridPane();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle r = new Rectangle(col * SIZE,row * SIZE , SIZE, SIZE);
                r.setFill( (col + row) % 2 == 0 ? Color.PALEVIOLETRED : Color.DARKSEAGREEN);
                gridPane.addRow(row,r);
            }
        }

        return gridPane;
    }
    @Override
    public Point getInput(Player player) {
        return null;
    }

    @Override
    public boolean askPieRule() {
        return false;
    }

    @Override
    public void notifyEndGame(Player player) {

    }

    @Override
    public int askSize() {
        int size = 3;
        TextInputDialog dialog = new TextInputDialog("11");
        dialog.setTitle("Enter Size");
        dialog.setHeaderText(null);
        dialog.setContentText("Please Enter the Size of the Board:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return Integer.parseInt(result.get());
        }
        Platform.exit();
        return 0;
    }

    @Override
    public void notifyPass() {

    }

    @Override
    public void display(Board board) {

    }

    @Override
    public void notifyInvalidMove() {

    }

    @Override
    public int initialize() {
        HBox initPane = new HBox();
        Button startButton = new Button("Start");
        startButton.setOnAction((ActionEvent e) -> {
            int size = askSize();
            initUI(size);});
        Button endButton = new Button("Quit");
        endButton.setOnAction((ActionEvent e) -> Platform.exit());
        Button rulesButton = new Button("Rules");
        rulesButton.setOnAction((ActionEvent e) -> System.out.println("RULES NOT AVAILABLE YET"));
        initPane.setPadding(new Insets(25, 25, 25, 25));
        initPane.getChildren().add(startButton);
        initPane.getChildren().add(endButton);
        initPane.getChildren().add(rulesButton);
        Scene scene = new Scene(initPane);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        return 1;
    }
}


