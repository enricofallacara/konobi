package core;

public class Player {
    private Color color;

    public Player(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public void changeSide() {
        color = (color == Color.black) ? Color.white : Color.black;
    }
}
