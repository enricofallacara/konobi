package user_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    private final int SIZE = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initUI(primaryStage);
    }

    private void initUI(Stage stage) {
        StackPane root = new StackPane();

        Pane board = new Pane();

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {

                Rectangle r = new Rectangle(col * SIZE, row * SIZE,
                        SIZE, SIZE);

                if ((col + row) % 2 == 0) {
                    r.setFill(Color.BLUE);
                } else {
                    r.setFill(Color.BEIGE);
                }

                board.getChildren().add(r);
            }

        }
        root.getChildren().add(board);

        Scene scene = new Scene(root, Color.WHITESMOKE);

        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }
}


