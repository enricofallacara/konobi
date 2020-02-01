package core;

import user_interface.Console;
import user_interface.UserInterface;

public class Konobi {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        Supervisor supervisor = new Supervisor(userInterface.initialize());

        while (!supervisor.isEndGame()) {
            userInterface.display(supervisor.getBoard());
            if (supervisor.isPassRule()) {
                userInterface.notifyPass();
                continue;
            }
            if (supervisor.isPieRule() && userInterface.askPieRule()) {
                supervisor.performPieRule();
                continue;
            }
            while (!supervisor.newMove(userInterface.getInput(supervisor.getCurrentPlayer()))) {
                userInterface.notifyInvalidMove();
            }
        }
        userInterface.display(supervisor.getBoard());
        userInterface.notifyEndGame(supervisor.getLastPlayer());
    }
}
