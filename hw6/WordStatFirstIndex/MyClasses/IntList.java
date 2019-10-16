package MyClasses;

import java.util.Arrays;

public class IntList {
    private int size;
    private int[] arr;

    public IntList(int value) {
        arr = new int[10];
        size = 0;
        add(value);
    }

    public void add(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, 3 * arr.length / 2);
        }
        arr[size++] = value;
    }

    public int get(int position) throws IndexOutOfBoundsException {
        if (position >= size || position < 0) {
            throw new IndexOutOfBoundsException("bad position");
        }
        return arr[position];
    }

    public int getSize() {
        return size;
    }
}