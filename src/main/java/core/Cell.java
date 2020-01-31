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

    public boolean hasThisColor(Color otherColor) { return color == otherColor; }
    public boolean hasSameColorAsPlayer(Player player) {return hasThisColor(player.getColor()); }

    public Point getCoordinates() {
        return coordinates;
    }
}
