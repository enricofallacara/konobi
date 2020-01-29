package user_interface;

import core.Supervisor;

public class Communicator {
    private Supervisor supervisor;
    private UserInterface userInterface;

    public Communicator(Supervisor s, UserInterface ui){
        supervisor = s;
        userInterface = ui;
    }


}
