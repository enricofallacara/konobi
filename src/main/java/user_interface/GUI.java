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

public class GUI extends Application {
    private final int SIZE = 50;
    private Stage stage;
    private GridPane gridPane;
    private Supervisor supervisor;


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        initialize();
    }

    public void initUI(int size) {

        supervisor = new Supervisor(size);
        GUIBoardFiller boardFiller = new GUIBoardFiller(size, SIZE);

        gridPane = boardFiller.createAndFillBoard();
        gridPane.setOnMouseClicked(e -> inputHandler(e.getX(), e.getY()));

        Scene scene = new Scene(gridPane, Color.WHITESMOKE);

        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }

    private int coordinateConversion(double coordinate) {
        return (int)coordinate/SIZE;
    }


    // TODO: ha senso metterla nella classe Cell o in una nuova classe?
    private static final Map<core.Color, Paint> colorPaintMap = new HashMap<>() {{
        put(core.Color.black, Color.BLACK);
        put(core.Color.white, Color.WHITE);
    }};

    public void addTile(GridPane gridPane, int X, int Y, Supervisor supervisor) {
        Rectangle rect = new Rectangle(X * SIZE, Y * SIZE, SIZE-12, SIZE-12);
        rect.setFill(colorPaintMap.get(supervisor.getLastPlayer().getColor()));
        gridPane.add(rect, X, Y);

    }

    private void inputHandler(double X, double Y) {

        int columnIndex = coordinateConversion(X);
        int rowIndex = coordinateConversion(Y);

        if(!supervisor.newMove(new Point(columnIndex, rowIndex))) {
            GUIMessageWriter.notifyInvalidMove();
            return;
        }

        /*
        Rectangle rect = new Rectangle(X * SIZE, Y * SIZE, SIZE-12, SIZE-12);
        rect.setFill(colorPaintMap.get(supervisor.getLastPlayer().getColor()));
        gridPane.add(rect, columnIndex, rowIndex);
         */
        addTile(gridPane, columnIndex, rowIndex, supervisor);

        if(Rulebook.queryRule(supervisor, EndGameRule::new)){
            GUIMessageWriter.notifyEndGame(supervisor.getLastPlayer());
            stop();
        }
        if(Rulebook.queryRule(supervisor, PieRule::new) && GUIAsker.askPieRule()){
            supervisor.performPieRule();
        }
        if(Rulebook.queryRule(supervisor, PassRule::new) ){
            supervisor.performPassRule();
            GUIMessageWriter.notifyPass();
        }
    }

    @Override
    public void stop(){
        Platform.exit();
    }




    public int initialize() {
        HBox initPane = new HBox();
        Button startButton = new Button("Start");
        startButton.setOnAction((ActionEvent e) -> {
            int size = GUIAsker.askSize();
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


