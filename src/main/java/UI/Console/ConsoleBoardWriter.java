package UI.Console;

import core.Entities.Board;
import core.Entities.Cell;
import java.awt.Point;
import java.util.stream.IntStream;


public class ConsoleBoardWriter {

    private static String padLeft(String s) { return String.format("%" + 3 + "s", s); }

    private static void displayCell(Cell cell) {
        System.out.print(ConsoleCellRepresentation.getRepresentation(cell.getColour()));
    }

    private static void displayRow(Board board, int y) {
        System.out.print(y + "\t");
        IntStream.range(0, board.getSize()).forEach(x -> displayCell(board.getCell(new Point(x, y))));
        System.out.println();
    }

    public static void displayBoard(Board board) {
        System.out.println();
        IntStream.iterate(board.getSize() - 1, x -> --x).limit(board.getSize()).forEach(y -> displayRow(board, y));
        System.out.print("\t");
        IntStream.range(0, board.getSize()).forEach(i -> System.out.print(padLeft(i + " ")));
        System.out.println("\n");
    }

}
