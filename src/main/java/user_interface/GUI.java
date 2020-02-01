package user_interface;

import core.Board;
import core.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class GUI extends Application implements UserInterface{
    private final int SIZE = 50;


   // public static void main(String[] args) {
  //      launch(args);
   // }

    @Override
    public void start(Stage primaryStage) {
        initUI(primaryStage);
    }

    private void initUI(Stage stage) {

        GridPane board = new GridPane();

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {

                Rectangle r = new Rectangle(col * SIZE, row * SIZE,
                        SIZE, SIZE);


                if ((col + row) % 2 == 0) {
                    r.setFill(Color.BLUE);
                } else {
                    r.setFill(Color.BEIGE);
                }

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

    }
}


