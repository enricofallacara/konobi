package core;

public class Player {
    private Color color;
    private String name;

    public Player(Color c) {
        color = c;
    }
    public Player(Color c, String n){
        color = c;
        name = n;
    }
    public Color getColor() {
        return color;
    }
    public String getName(){ return name;}

    public Color getOppositeColor() {
        return (color == Color.black) ? Color.white : Color.black;
    }

    public void changeSide() {
        color = getOppositeColor();
    }
}
