package UI.GUI;

import UI.GUI.Events.EndGameEvent;
import UI.GUI.Events.PassRuleEvent;
import UI.GUI.Events.PieRuleEvent;
import UI.GUI.Handlers.GUIEndGameHandler;
import UI.GUI.Handlers.GUIMouseInputHandler;
import UI.GUI.Handlers.GUIPassRuleHandler;
import UI.GUI.Handlers.GUIPieRuleHandler;
import core.Entities.StatusSupervisor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {

    private final int tilesize = 50;
    private Stage stage;
    private GridPane gridPane;
    private StatusSupervisor supervisor;
    private GUIBoardWriter boardFiller;

    public GridPane getGridBoard() { return (GridPane)gridPane.getChildren().get(0); }
    public GridPane getLabelBoard() { return (GridPane)gridPane.getChildren().get(1); }
    public StatusSupervisor getSupervisor() { return supervisor; }
    public GUIBoardWriter getBoardFiller() { return boardFiller; }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        initUI();
    }

    // TODO: Long Method smell?
    // No, perchè come hanno detto loro, purtoppo i metodi che cpopolano un interfaccia saranno sempre quantomeno "lunghi"
    // perchè le compomnenti, in una parte o nell altra, devono essere comunque aggiunte e devono venire gestiti gli eventi.
    // Al massimo si può pensare di fare un metodo  che esegua i createAndSetHandlerOnNode() ma non so quanto senso abbia
    private void initGameInterface(int boardSize) {
        gridPane = new GridPane();
        gridPane.setVgap(20);
        supervisor = new StatusSupervisor(boardSize);
        boardFiller = new GUIBoardWriter(boardSize, tilesize);

        GridPane gridBoard = boardFiller.createEmptyBoard();
        gridBoard.getStyleClass().add("grid-board");

        GridPane labelBoard = boardFiller.createLabelPane();
        labelBoard.getStyleClass().add("label-board");

        createAndSetHandlerOnNode(gridBoard, MouseEvent.MOUSE_CLICKED, new GUIMouseInputHandler(this));
        createAndSetHandlerOnNode(gridBoard, EndGameEvent.END_GAME_EVENT_TYPE, new GUIEndGameHandler(this));
        createAndSetHandlerOnNode(gridBoard, PassRuleEvent.PASS_RULE_EVENT_TYPE, new GUIPassRuleHandler(this));
        createAndSetHandlerOnNode(gridBoard, PieRuleEvent.PIE_RULE_EVENT_TYPE, new GUIPieRuleHandler(this));

        gridPane.add(gridBoard, 0, 0);
        gridPane.add(labelBoard, 0, 1);
        gridPane.getStyleClass().add("grid-pane");

        Scene scene = new Scene(gridPane, Color.WHITESMOKE);

        String path = getClass().getResource("/GUI.css").toExternalForm();
        scene.getStylesheets().add(path);

        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }

    private <T extends Event> void createAndSetHandlerOnNode(Node source, EventType<T> eventType, EventHandler<? super T> eventHandler) {
        source.addEventHandler(eventType, eventHandler);
    }

    public int coordinateConversion(double coordinate) {
        return (int)coordinate / tilesize;
    }

    @Override
    public void stop(){
        Platform.exit();
    }

    private Button createAndSetButton(String text, int width, int height, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setOnAction(handler);
        return button;
    }

    // TODO: Long Method smell?
    // No, idem with potatoes.
    private void initUI() {
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setVgap(20);

        Text text = new Text("Konobi Game");
        text.setFont(Font.font("Tahoma", 40));

        pane.add(text, 0, 0 );
        GridPane.setHalignment(text, HPos.CENTER);

        int width = 80;
        int height = 35;

        Button startButton = createAndSetButton("Start", width, height, (ActionEvent e) -> {
            int size = GUIAsker.askSize();
            initGameInterface(size);});

        Button endButton = createAndSetButton("Exit", width, height, (ActionEvent e) -> stop());

        Button rulesButton = createAndSetButton("Rules", width, height, (ActionEvent e) -> getHostServices().showDocument("https://boardgamegeek.com/boardgame/123213/konobi"));

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

    public static void main(String[] args) { Application.launch(args); }

}
