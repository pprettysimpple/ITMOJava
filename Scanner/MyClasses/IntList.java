package MyClasses;

import java.util.Arrays;

public class IntList {
    private int size;
    private int[] arr;

    public IntList() {
        initialize();
    }

    public IntList(int value) {
        initialize();
        add(value);
    }

    private void initialize() {
        arr = new int[10];
        size = 0;
    }

    public void add(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, 3 * arr.length / 2);
        }
        arr[size++] = value;
    }

    public int get(int position) {
        return arr[position];
    }

    public int getSize() {
        return size;
    }
}