package MNK;

public class Cell {
    private char color;

    public Cell() {
        new Cell('E');
    }

    public Cell(char color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            return color == ((Cell) obj).color;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return color;
    }
}
