package core;

import user_interface.Console;
import user_interface.UserInterface;

public class Konobi {

    UserInterface userInterface;
    Supervisor supervisor;

    Konobi() {
        userInterface = new Console();
        // Forse ha senso dividere initalise() e askSize();
        supervisor = new Supervisor(userInterface.initialize());
    }

    public void play() {
        while(!supervisor.isEndGame()) {
            playTurn();

        }

    }

    private void playTurn() {
        System.out.println("Turn");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
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
    */
}
