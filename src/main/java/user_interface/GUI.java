package user_interface;

import core.Board;
import core.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.IOException;
import java.util.Optional;

public class GUI extends Application implements UserInterface{
    private final int SIZE = 50;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        initUI();
    }

    public void initUI() {

        GridPane board = new GridPane();

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                Rectangle r = new Rectangle(col * SIZE,row * SIZE , SIZE, SIZE);
                r.setFill( (col + row) % 2 == 0 ? Color.BEIGE : Color.BLUE);
                board.addRow(row,r);
            }
        }

        board.setOnMouseClicked(e -> {Rectangle rect = new Rectangle(e.getX() * SIZE, e.getY() * SIZE, SIZE, SIZE);
            rect.setFill(Color.BLACK);
            board.add(rect, (int)e.getX()/SIZE, (int)e.getY()/SIZE);
        });

        Scene scene = new Scene(board, Color.WHITESMOKE);
        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();





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
    public void initialize() {
        HBox initPane = new HBox();
        Button startButton = new Button("Start");
        startButton.setOnAction((ActionEvent e) -> System.out.println("start"));
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
    }
}


