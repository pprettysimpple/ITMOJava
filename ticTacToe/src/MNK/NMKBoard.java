package MNK;

import java.util.HashMap;
import java.util.Map;

public class NMKBoard implements Board, Position {

    private final char EMPTY_CHAR = 'Z';
    private final Cell EMPTY_CELL = new Cell(EMPTY_CHAR);
    private Map<Cell, Character> SYMBOLS;

    private final Cell[][] cells;
    private final int k;
    private int filledCount = 0;
    private final int playerCount;
    private char turn;

    public NMKBoard(int n, int m, int k, int playerCount) {
        this.cells = new Cell[n][m];
        this.k = k;
        this.playerCount = playerCount;
        turn = 'A';
        SYMBOLS = new HashMap<>();
        for (char i = 0; i < playerCount; i++) {
            SYMBOLS.put((new Cell((char) ('A' + i))), (char) ('A' + i));
        }
        SYMBOLS.put(EMPTY_CELL, '.');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell(EMPTY_CHAR);
            }
        }
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
    }

    @Override
    public Cell getCell() {
        return new Cell(turn);
    }

    private int getCnt(int r, int c, int i, int j) {
        int cnt = 0;
        while (r + i >= 0 && c + j >= 0
                && r + i < cells.length
                && c + j < cells[0].length
                && cells[r + i][c + j].equals(new Cell(turn))) {
            cnt++;
            r += i;
            c += j;
        }
        return cnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int r = move.getRow(), c = move.getColumn();
        cells[r][c] = move.getValue();

        if (won(r, c, 0, 1) || won(r, c, 1, 0)
                || won(r, c, 1, 1) || won(r, c, 1, -1)) {
            return Result.WIN;
        }

        if (++filledCount == cells.length * cells[r].length) {
            return Result.DRAW;
        }

        turn++;
        if (turn - 'A' == playerCount) {
            turn = 'A';
        }
        return Result.UNKNOWN;
    }

    private boolean won(int r, int c, int d1, int d2) {
        return getCnt(r, c, d1, d2) + getCnt(r, c, -d1, -d2) + 1 >= k;
    }

    @Override
    public int getN() {
        return cells.length;
    }

    @Override
    public int getM() {
        return cells[0].length;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()].equals(EMPTY_CELL)
                && getCell().equals(new Cell(turn));
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int width = Math.max(
                String.valueOf(cells[0].length).length(),
                String.valueOf(cells.length).length()
        );

        final StringBuilder sb = new StringBuilder(" ".repeat(width + 1));
        String digitPattern = "%" + width + "d";
        String charPattern = "%" + width + "c";

        for (int c = 0; c < cells[0].length; c++) {
            sb.append(String.format(digitPattern, c + 1)).append(" ");
        }
        for (int r = 0; r < cells.length; r++) {
            sb.append("\n");
            sb.append(String.format(digitPattern, r + 1)).append(" ");
            for (int c = 0; c < cells[0].length; c++) {
                sb.append(String.format(charPattern, SYMBOLS.get(cells[r][c]))).append(" ");
            }
        }

        return sb.toString();
    }
}
