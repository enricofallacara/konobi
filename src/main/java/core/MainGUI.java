package core;

import UI.GUI.GUI;
import javafx.application.Application;


public class MainGUI {

    public static void main(String[] args) {
        new Thread(() -> Application.launch(GUI.class)).start();
    }

}
