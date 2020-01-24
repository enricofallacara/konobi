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
        // NEED TO THROW EXCEPTION IF CALLED UPON BLACK PLAYER
        color = Color.black;
    }
}
