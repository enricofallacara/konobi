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

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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




    public void initialize() throws FileNotFoundException {

        Pane pane =  new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF"),CornerRadii.EMPTY, Insets.EMPTY)));

        Image image1 = new Image(new FileInputStream("logo.png"));
        ImageView imgView = new ImageView(image1);
        imgView.setX(0);
        imgView.setY(5);

        pane.getChildren().add(imgView);

        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);
        startButton.setPrefHeight(35);
        startButton.setOnAction((ActionEvent e) -> {
            int size = GUIAsker.askSize();
            initUI(size);});

        Button endButton = new Button("Quit");
        endButton.setPrefWidth(80);
        endButton.setPrefHeight(35);
        endButton.setOnAction((ActionEvent e) -> Platform.exit());

        Button rulesButton = new Button("Rules");
        rulesButton.setPrefWidth(80);
        rulesButton.setPrefHeight(35);
        rulesButton.setOnAction((ActionEvent e) -> System.out.println("RULES NOT AVAILABLE YET"));

        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.getChildren().addAll(startButton,endButton,rulesButton);
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


