package user_interface;

import core.Board;
import core.Cell;

import java.awt.*;
import java.util.stream.IntStream;

public class ConsoleBoardWriter {

    private void displayCell(Cell cell) {
        System.out.print(ConsoleCellRepresentation.getRepresentation(cell.getColor()));
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
        IntStream.iterate(board.getSize() - 1, x -> --x).limit(board.getSize()).forEach(y -> displayRow(board, y));

        System.out.print("\t");
        IntStream.range(0, board.getSize()).forEach(i -> System.out.print(padLeft(i + " ")));

        System.out.println("\n");
    }

}
