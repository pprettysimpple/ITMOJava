//package MNK;
//
//public class WinnerPlayer implements Player {
//    int recursionLimit;
//
//    WinnerPlayer() {
//        new WinnerPlayer(3);
//    }
//
//    WinnerPlayer(int recursionLimit) {
//        this.recursionLimit = recursionLimit;
//    }
//
//    int getStateValue(Position position, Cell turn) {
//        int maxSegment = 0;
//        for (int r = 0; r < position.getN(); r++) {
//            for (int c = 0; c < position.getM(); c++) {
//                int cnt = 0;
//                while (c + cnt < position.getM() && position.getCell(r, c + cnt) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//
//            for (int c = 0; c < Math.min(position.getM(), position.getN() - r); c++) {
//                int cnt = 0;
//                while (c + cnt < position.getM() && position.getCell(r + c, c + cnt) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//
//            for (int c = 0; c < Math.min(position.getM(), r + 1); c++) {
//                int cnt = 0;
//                while (c + cnt < position.getM() && position.getCell(r - c, c + cnt) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//        }
//
//        for (int c = 0; c < position.getM(); c++) {
//            for (int r = 0; r < position.getN(); r++) {
//                int cnt = 0;
//                while (r + cnt < position.getN() && position.getCell(r + cnt, c) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//
//            for (int r = 0; r < Math.min(position.getN(), position.getM() - c); r++) {
//                int cnt = 0;
//                while (r + cnt < position.getN() && position.getCell(r + cnt, c + r) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//
//            for (int r = 0; r < Math.min(position.getN(), c + 1); r++) {
//                int cnt = 0;
//                while (r + cnt < position.getN() && position.getCell(r + cnt, c - r) == turn) {
//                    cnt++;
//                }
//                maxSegment = Math.max(cnt, maxSegment);
//            }
//        }
//
//        return maxSegment;
//    }
//
//    int tryThisBoard(Cell[][] cells, Cell turn, int k, int prev_current, int depth) {
//        if (depth == recursionLimit) {
//            return getStateValue(new NMKBoard(cells, turn, k), turn) + 1;
//        }
//        int current = -1000;
//        for (int r = 0; r < cells.length; r++) {
//            for (int c = 0; c < cells[r].length; c++) {
//                NMKBoard board = new NMKBoard(cells, turn, k);
//                if (board.isValid(new Move(r, c, turn))) {
//                    Cell saved = cells[r][c];
//                    switch (board.makeMove(new Move(r, c, turn))) {
//                        case WIN:
//                            return 1000;
//                        case LOSE:
//                            return -1000;
//                        case DRAW:
//                            return 0;
//                    }
////                    System.err.println(board);
////                    System.err.println("----");
//                    int tmp = -tryThisBoard(cells, swapTurn(turn), k, current, depth + 1);
//                    cells[r][c] = saved;
//                    if (current < tmp) {
//                        current = tmp;
////                        if (-current < prev_current) {
////                            return current;
////                        }
//                    }
//                }
//            }
//        }
//        return current;
//    }
//
//    private Cell swapTurn(Cell turn) {
//        return (turn == Cell.X ? Cell.O : Cell.X);
//    }
//
//    @Override
//    public Move move(Position position, Cell turn) {
//        Cell[][] cells = new Cell[position.getN()][position.getM()];
//        for (int r = 0; r < cells.length; r++) {
//            for (int c = 0; c < cells[r].length; c++) {
//                cells[r][c] = position.getCell(r, c);
//            }
//        }
//        int opt_r = -1, opt_c = -1;
//        int opt_weight = -10000;
//        for (int r = 0; r < cells.length; r++) {
//            for (int c = 0; c < cells[r].length; c++) {
//                if (position.isValid(new Move(r, c, turn))) {
//                    Cell saved = position.getCell(r, c);
//                    cells[r][c] = turn;
//                    int current = -tryThisBoard(cells, swapTurn(turn), position.getK(), -1000, 0);
//                    cells[r][c] = saved;
//                    if (current > opt_weight) {
//                        opt_r = r;
//                        opt_c = c;
//                        opt_weight = current;
//                    }
//                }
//            }
//        }
//        Move ans = new Move(opt_r, opt_c, turn);
//        return ans;
//    }
//}
