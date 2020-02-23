package UI;

import core.Entities.Player;


public interface MessageWriter {

    void notifyPass();

    void notifyEndGame(Player player);

    void notifyInvalidMove();

    void notifyPieRule();

}
