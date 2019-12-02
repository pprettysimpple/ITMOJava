package MNK;

public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
    int getN();
    int getM();
    int getK();
}
