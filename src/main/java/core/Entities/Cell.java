package core.Entities;

import java.awt.Point;


public class Cell {

    private Colour colour;
    private final Point coordinates;

    public Cell(Point p) {
        colour = null;
        coordinates = p;
    }

    public Colour getColour() {
        return colour;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setColour(Colour c) {
        colour = c;
    }

    public boolean hasThisColour(Colour c) { return colour == c; }

}
