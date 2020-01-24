package core;

public class Player {
    private cellStatus color;

    public Player(cellStatus c) {
        color = c;
    }

    public cellStatus getColor() {
        return color;
    }

    public void changeSide() {
        color = cellStatus.black;
    }
}
