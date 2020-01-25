package core;

public class Player {
    private Color color;

    public Player(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public Color getOppositeColor() {
        return (color == Color.black) ? Color.white : Color.black;
    }

    public void changeSide() {
        color = getOppositeColor();
    }
}
