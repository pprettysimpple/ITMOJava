package MyClasses;

import java.util.Arrays;

public class IntList {
    private int size;
    private int[] arr;
    private int lastLine;
    private int count;

    public IntList(int value, int lastLine) {
        initialize();
        addToList(value);
        this.lastLine = lastLine;
        count = 1;
    }

    private void initialize() {
        arr = new int[10];
        size = 0;
    }

    public void add(int position, int lineNum) {
        count++;
        if (lastLine != lineNum) {
            addToList(position);
            lastLine = lineNum;
        }
    }

    private void addToList(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, 3 * arr.length / 2);
        }
        arr[size++] = value;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(count).append(' ');
        for (int i = 0; i < size; i++) {
            result.append(arr[i]);
            if (i + 1 != size) {
                result.append(' ');
            }
        }
        return result.toString();
    }
}