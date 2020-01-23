package core;

import java.awt.*;

public class Cell {

    public Cell(Point p) {
        color = cellStatus.empty;
        coordinates = p;
    }

    private cellStatus color;
    private Point coordinates;

    public cellStatus getColor() {
        return color;
    }

    public void setColor( cellStatus c) {
        color = c;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}
