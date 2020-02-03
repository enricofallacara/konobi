package user_interface;

import core.*;
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
import java.util.concurrent.TimeUnit;

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
        gridPane.setOnMouseClicked(e -> inputHandler(e.getX(), e.getY()));

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
        int columnIndex = coordinateConversion(X);
        int rowIndex = coordinateConversion(Y);


        if(!supervisor.newMove(new Point(columnIndex, rowIndex))) {
            notifyInvalidMove();
            return;
        }

        Rectangle rect = new Rectangle(X * SIZE, Y * SIZE, SIZE-12, SIZE-12);
        rect.setFill(colorPaintMap.get(supervisor.getLastPlayer().getColor()));
        gridPane.add(rect, columnIndex, rowIndex);

        if(Rulebook.queryRule(supervisor, EndGameRule::new)){
            notifyEndGame(supervisor.getLastPlayer());
            stop();
        }
        if(Rulebook.queryRule(supervisor, PieRule::new) && askPieRule()){
            supervisor.performPieRule();
        }
        if(Rulebook.queryRule(supervisor, PassRule::new) ){
            supervisor.performPassRule();
            notifyPass();
        }



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
    public void stop(){

        Platform.exit();
    }

    @Override
    public Point getInput(Player player) {
        return null;
    }

    @Override
    public boolean askPieRule() {
        // TODO: aggiungere delay
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pie Rule Dialog");
        alert.setContentText("PlayerTwo: Do you want to apply the Pie Rule?");
        alert.setHeaderText(null);

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();

    }

    @Override
    public void notifyEndGame(Player player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game Information");
        alert.setHeaderText(null);
        alert.setContentText(supervisor.getLastPlayer().getName() + " has won!");
        alert.showAndWait();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pass Rule Information");
        alert.setHeaderText(null);
        alert.setContentText("You shall pass!");
        alert.showAndWait();
    }

    @Override
    public void display(Board board) {

    }

    @Override
    public void notifyInvalidMove() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Move Information");
        alert.setHeaderText(null);
        alert.setContentText("Invalid move! Try again!");
        alert.showAndWait();
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


