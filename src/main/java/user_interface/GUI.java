package user_interface;

import core.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {
    private final int TILESIZE = 50;
    private Stage stage;
    private GridPane gridPane;
    private Supervisor supervisor;
    private GUIBoardFiller boardFiller;

    public GridPane getGridBoard() { return (GridPane)gridPane.getChildren().get(0); }
    public GridPane getLabelBoard() { return (GridPane)gridPane.getChildren().get(1); }
    public Supervisor getSupervisor() { return supervisor; }
    public GUIBoardFiller getBoardFiller() { return boardFiller; }


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        initialize();
    }

    public void initUI(int boardSize) {

        gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setVgap(20);
        supervisor = new Supervisor(boardSize);
        boardFiller = new GUIBoardFiller(boardSize, TILESIZE);

        GridPane gridBoard = boardFiller.createEmptyBoard();
        gridBoard.setGridLinesVisible(true);

        GridPane labelBoard = boardFiller.createLabelPane();
        GUIInputHandler inputHandler = new GUIInputHandler(this);
        gridBoard.setOnMouseClicked(inputHandler);

        gridPane.add(gridBoard, 0, 0);
        gridPane.add(labelBoard, 0, 1);



        Scene scene = new Scene(gridPane, Color.WHITESMOKE);
        // TODO: questo risolve automaticamente il problema del resize, ma bisogna importare il jar
        /*SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);*/


        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }
    // TODO: forse privato in GUIInputHandler
    public int coordinateConversion(double coordinate) {
        return (int)coordinate / TILESIZE;
    }

    @Override
    public void stop(){
        Platform.exit();
    }


    public Button createButton(String text, int width, int height) {
        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        return button;
    }

    // TODO: forse messa in GUIBoardFiller?
    public void switchLabelsColors(){
        GridPane labelBoard = getLabelBoard();
        Circle cBlack = (Circle) labelBoard.getChildren().get(2);
        cBlack.setFill(Color.WHITE);
        Circle cWhite = (Circle) labelBoard.getChildren().get(3);
        cWhite.setFill(Color.BLACK);
    }


    public void initialize() {

        GridPane pane = new GridPane();

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setVgap(20);

        Text text = new Text("Konobi Game");
        text.setFont(Font.font("Tahoma", 40));

        pane.add(text, 0, 0 );
        GridPane.setHalignment(text, HPos.CENTER);

        int width = 80;
        int height = 35;

        Button startButton = createButton("Start", width, height);
        startButton.setOnAction((ActionEvent e) -> {
            int size = GUIAsker.askSize();
            initUI(size);});

        Button endButton = createButton("Exit", width, height);
        endButton.setOnAction((ActionEvent e) -> Platform.exit());

        Button rulesButton = createButton("Rules", width, height);
        rulesButton.setOnAction((ActionEvent e) -> getHostServices().showDocument("https://boardgamegeek.com/boardgame/123213/konobi"));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(startButton, endButton, rulesButton);

        pane.add(hBox, 0, 1);
        hBox.setSpacing(15);
        GridPane.setHalignment(hBox, HPos.CENTER);

        Scene scene = new Scene(pane);

        stage.setTitle("Konobi");
        stage.setScene(scene);
        stage.show();
    }

}


