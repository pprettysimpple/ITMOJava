package MNK;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    Move scanMove(Cell turn) {
        int row, column;
        Scanner line = new Scanner(in.nextLine());
        try {
            row = line.nextInt();
            column = line.nextInt();
        } catch (NoSuchElementException | NumberFormatException e) {
            return new Move(-1, -1, turn);
        }
        if (in.hasNext()) {
            return new Move(-1, -1, turn);
        }
        return new Move(row, column, turn);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            final Move move = scanMove(cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
