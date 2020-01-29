package core;

import user_interface.Console;
import user_interface.UserInterface;

import java.awt.*;

public class Konobi {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        Supervisor supervisor = new Supervisor(userInterface.askSize());
        while (!supervisor.isEndGame()) {
            userInterface.display(supervisor.getBoard());
            if (supervisor.isPassRule()) {
                userInterface.notifyPass();
            }
            else if (supervisor.isPieRule()) {
                supervisor.performPieRule(userInterface.askPieRule());
            }
            else {
                while (!supervisor.newMove(userInterface.getInput())) {
                    userInterface.notifyInvalidMove();
                }
            }
        }
        userInterface.notifyEndGame(supervisor.getCurrentPlayer());
    }
}
