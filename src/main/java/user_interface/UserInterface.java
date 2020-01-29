package user_interface;

import core.Board;
import core.Player;
import java.awt.*;

public interface UserInterface {

    Point getInput();
    boolean askPieRule();
    void notifyEndGame(Player player);
    int askSize();
    void notifyPass();
    void display(Board board);

}
