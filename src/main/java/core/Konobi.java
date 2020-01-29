package core;

import user_interface.Console;
import user_interface.UserInterface;

import java.awt.*;

public class Konobi {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        int size = userInterface.askSize();
        Supervisor supervisor = new Supervisor(size);
        while (!supervisor.isEndGame()) {
            if (supervisor.isPassRule()) {
                userInterface.notifyPass();
            }
            else if (supervisor.isPieRule()) {
                if (userInterface.askPieRule()) {
                    supervisor.performPieRule();
                }
            }
            else {
                Point newPoint = userInterface.getInput();
                supervisor.newMove(newPoint);
            }
        }
        userInterface.endGame(supervisor.getCurrentPlayer());
    }
}
