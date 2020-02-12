package core.Entities;

import java.awt.Point;


public class Cell {

    private Color color;
    private final Point coordinates;

    public Cell(Point p) {
        color = null;
        coordinates = p;
    }

    public Color getColor() {
        return color;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setColor(Color c) {
        color = c;
    }

    public boolean hasThisColor(Color otherColor) { return color == otherColor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        if (!this.hasThisColor(cell.getColor())) return false;
        return coordinates == ((Cell) o).getCoordinates();
    }

}
