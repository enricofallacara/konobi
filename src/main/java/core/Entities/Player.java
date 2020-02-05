package core.Entities;

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

    public void changeSide() {
        color = color.getOppositeColor();
    }
}
