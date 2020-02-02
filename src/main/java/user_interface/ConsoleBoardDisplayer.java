package user_interface;

import core.Board;
import core.Cell;
import core.Color;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ConsoleBoardDisplayer {

    private final Map<core.Color, String> cellColorMap = new HashMap<>() {{
        put(core.Color.black, "[\u25A0]");
        put(Color.white, "[\u25A1]");
        put(null, "[ ]");
    }};

    private void displayCell(Cell cell) {
        System.out.print(cellColorMap.get(cell.getColor()));
    }

    private void displayRow(Board board, int y) {
        System.out.print(y + "\t");
        IntStream.range(0, board.getSize()).forEach(x -> displayCell(board.getCell(new Point(x, y))));
        System.out.println();
    }

    private String padLeft(String s) {
        return String.format("%" + 3 + "s", s);
    }

    public void displayBoard(Board board) {
        System.out.println("\f");
        IntStream.iterate(board.getSize() - 1, x -> --x).limit(board.getSize()).forEach(y -> displayRow(board, y));

        System.out.print("\t");
        IntStream.range(0, board.getSize()).forEach(i -> System.out.print(padLeft(i + " ")));

        System.out.println();
    }

}
