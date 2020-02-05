package core;

import core.Entities.Konobi;
import javafx.application.Application;
import UI.GUI.GUI;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        if (Arrays.asList(args).contains("--gui")) {
            new Thread(() -> Application.launch(GUI.class)).start();
        } else {
            Konobi game = new Konobi();
            game.play();
        }
    }
}
