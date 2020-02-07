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
    public boolean hasSameColorAsPlayer(Player player) {return hasThisColor(player.getColor()); }

}
