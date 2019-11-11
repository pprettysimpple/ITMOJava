package md2html;

import java.util.Arrays;

class IntList {
    private int size = 0;
    private int[] arr = new int[10];

    void add(int value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, 3 * arr.length / 2);
        }
        arr[size++] = value;
    }

    void pop() {
        size--;
    }

    int get(int position) {
        return arr[position];
    }

    int getSize() {
        return size;
    }
}