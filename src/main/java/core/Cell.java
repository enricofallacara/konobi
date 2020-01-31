package core;

import java.awt.Point;

public class Cell {

    public Cell(Point p) {
        color = null;
        coordinates = p;
    }

    private Color color;
    private final Point coordinates;

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}
