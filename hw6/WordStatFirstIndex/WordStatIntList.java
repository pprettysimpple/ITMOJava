import MyClasses.IntList;


public class WordStatIntList {
    private int lastLine;
    private int count;
    private IntList arr;

    public WordStatIntList(int value, int lastLine) {
        arr = new IntList(value);
        this.lastLine = lastLine;
        this.count = 1;
    }

    public void add(int position, int lineNum) {
        count++;
        if (lastLine != lineNum) {
            arr.add(position);
            lastLine = lineNum;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(count).append(' ');
        for (int i = 0; i < arr.getSize(); i++) {
            result.append(arr.get(i));
            if (i + 1 != arr.getSize()) {
                result.append(' ');
            }
        }
        return result.toString();
    }
}