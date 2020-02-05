package UI.GUI;


import UI.GUI.Events.EndGameEvent;
import UI.GUI.Events.PassRuleEvent;
import UI.GUI.Events.PieRuleEvent;
import UI.GUI.Handlers.GUIEndGameHandler;
import UI.GUI.Handlers.GUIMouseInputHandler;
import UI.GUI.Handlers.GUIPassRuleHandler;
import UI.GUI.Handlers.GUIPieRuleHandler;
import core.Entities.Supervisor;
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

        createAndSetHandlerOnNode(gridBoard, MouseEvent.MOUSE_CLICKED, new GUIMouseInputHandler(this));
        createAndSetHandlerOnNode(gridBoard, EndGameEvent.END_GAME_EVENT_TYPE, new GUIEndGameHandler(this));
        createAndSetHandlerOnNode(gridBoard, PassRuleEvent.PASS_RULE_EVENT_TYPE, new GUIPassRuleHandler(this));
        createAndSetHandlerOnNode(gridBoard, PieRuleEvent.PIE_RULE_EVENT_TYPE, new GUIPieRuleHandler(this));

        gridPane.add(gridBoard, 0, 0);
        gridPane.add(labelBoard, 0, 1);

        Scene scene = new Scene(gridPane, Color.WHITESMOKE);

        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }

    public <T extends Event> void createAndSetHandlerOnNode(Node source, EventType<T> eventType, EventHandler<? super T> eventHandler) {
        source.addEventHandler(eventType, eventHandler);
    }

    public int coordinateConversion(double coordinate) {
        return (int)coordinate / TILESIZE;
    }

    @Override
    public void stop(){
        Platform.exit();
    }

    public Button cretaeAndSetButton(String text, int width, int height, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setOnAction(handler);
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

        Button startButton = cretaeAndSetButton("Start", width, height, (ActionEvent e) -> {
            int size = GUIAsker.askSize();
            initUI(size);});

        Button endButton = cretaeAndSetButton("Exit", width, height, (ActionEvent e) -> stop());

        Button rulesButton = cretaeAndSetButton("Rules", width, height, (ActionEvent e) -> getHostServices().showDocument("https://boardgamegeek.com/boardgame/123213/konobi"));

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

    public static void main(String[] args) {
        Application.launch(args);
    }

}


