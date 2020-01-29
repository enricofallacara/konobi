package user_interface;

import core.Player;
import java.awt.*;

public interface UserInterface {

    Point getInput();
    boolean askPieRule();
    void endGame(Player player);
    int askSize();
    void notifyPass();

}
