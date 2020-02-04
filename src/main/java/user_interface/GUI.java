package user_interface;

import core.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.BinaryOperator;

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
    public void start(Stage primaryStage) throws FileNotFoundException {
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
    }/*

    private void inputHandler(double X, double Y) {

        int columnIndex = coordinateConversion(X);
        int rowIndex = coordinateConversion(Y);

        if(!supervisor.newMove(new Point(columnIndex, rowIndex))) {
            GUIMessageWriter.notifyInvalidMove();
            return;
        }

        boardFiller.addPiece(gridPane, columnIndex, rowIndex, supervisor.getLastPlayer());

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
    }*/

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


    public void initialize() throws FileNotFoundException {

        Pane pane =  new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF"),
                CornerRadii.EMPTY, new Insets(25, 25, 25, 25))));

        Image logo = new Image(new FileInputStream("logo.png"));
        ImageView imgView = new ImageView(logo);
        imgView.setX(0);
        imgView.setY(5);

        pane.getChildren().add(imgView);

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
        hBox.setSpacing(15);
        hBox.getChildren().addAll(startButton, endButton, rulesButton);
        hBox.setPadding(new Insets(25, 25, 25, 25));
        hBox.setLayoutX(60);
        hBox.setLayoutY(50);

        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane);

        stage.setTitle("Konobi");
        stage.setScene(scene);
        stage.show();
    }
}


