package MNK;

import java.io.PrintStream;
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

    private Move incorrectMove() {
        return new Move(-1, -1, new Cell((char)0));
    }

    private Move scanMove(Cell turn) {
        int row, column;
        Scanner line = new Scanner(in.nextLine());

        if (line.hasNextInt()) {
            row = line.nextInt();

            if (line.hasNextInt()) {
                column = line.nextInt();

                if (line.hasNext()) {
                    return incorrectMove();
                }
                return new Move(row, column, turn);
            }
        }
        return incorrectMove();
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column in same line");
            final Move move = scanMove(cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
