package queue;

import queue.ArrayQueueModule;

public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " +
                    ArrayQueueModule.element() + " " + ArrayQueueModule.dequeue());
        }
    }

    public static void main(String[] args) {
        fill();
        dump();
    }
}
