package md2html;

import java.util.Arrays;

public class IntList {
    private int size = 0;
    private int[] arr = new int[5];

    public void add(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, 3 * arr.length / 2);
        }
        arr[size++] = value;
    }

    public int get(int position) {
        return arr[position];
    }

    public void pop() {
        size--;
    }

    public int getSize() {
        return size;
    }
}