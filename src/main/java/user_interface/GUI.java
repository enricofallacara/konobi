package user_interface;

import core.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {
    private final int TILESIZE = 50;
    private Stage stage;
    private GridPane gridPane;
    private Supervisor supervisor;
    private GUIBoardFiller boardFiller;

    public GridPane getPane() { return gridPane; }
    public Supervisor getSupervisor() { return supervisor; }
    public GUIBoardFiller getBoardFiller() { return boardFiller; }


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        initialize();
    }

    public void initUI(int boardSize) {

        supervisor = new Supervisor(boardSize);
        boardFiller = new GUIBoardFiller(boardSize, TILESIZE);

        gridPane = boardFiller.createEmptyBoard();
        gridPane.setGridLinesVisible(true);

        GUIInputHandler inputHandler = new GUIInputHandler(this);
        gridPane.setOnMouseClicked(inputHandler);

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
        rulesButton.setOnAction((ActionEvent e) -> System.out.println("RULES NOT AVAILABLE YET"));

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


