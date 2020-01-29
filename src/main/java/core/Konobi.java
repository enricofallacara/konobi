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
            //System.out.println("siamo qua");
            if (supervisor.isPassRule()) {
                userInterface.notifyPass();
            }
            else if (supervisor.isPieRule()) {
                //System.out.println("siamo dopo pie rule");
                if (userInterface.askPieRule()) {
                    supervisor.performPieRule();
                }
            }
            else {
                //System.out.println("siamo prima dell'input");
                Point newPoint = userInterface.getInput();
                supervisor.newMove(newPoint);
            }
            //System.out.println("siamo prima di endgame");
        }
        userInterface.endGame(supervisor.getCurrentPlayer());
    }
}
