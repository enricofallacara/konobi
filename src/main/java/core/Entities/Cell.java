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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        if (!this.hasThisColour(cell.getColour())) return false;
        return coordinates == ((Cell) o).getCoordinates();
    }

}
