package core.Entities;


public class Player {

    private Colour colour;
    private String name;

    public Player(Colour c) {
        colour = c;
    }

    public Player(Colour c, String n){
        colour = c;
        name = n;
    }

    public Colour getColour() {
        return colour;
    }

    public String getName(){ return name;}

    public void changeSide() {
        colour = colour.getOppositeColor();
    }

}
