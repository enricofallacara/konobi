package user_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        initUI(primaryStage);
    }
    private void initUI(Stage stage) {

        Pane root = new Pane();

        Rectangle rect = new Rectangle(25, 25, 50, 50);
        rect.setFill(Color.CADETBLUE);

        Line line = new Line(90, 40, 230, 40);
        line.setStroke(Color.BLACK);

        Circle circle = new Circle(130, 130, 30);
        circle.setFill(Color.CHOCOLATE);

        root.getChildren().addAll(rect, line, circle);

        Scene scene = new Scene(root, 250, 220, Color.WHITESMOKE);

        stage.setTitle("Absolute layout");
        stage.setScene(scene);
        stage.show();
    }

}