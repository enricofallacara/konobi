package core;

import java.awt.*;

public class Board {
    public Board(int s){
        size = s;  // necessary to add a control on value of s > 0
        grid = new Cell[s][s];
    }
    private int size;
    private Cell[][] grid;

    public int getSize(){ return size;}
    public Cell getCell(Point p){ return grid[p.x][p.y];}

}
