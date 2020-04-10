package queue;

import queue.ArrayQueueADT;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT stack) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(stack, i);
        }
    }

    public static void dump(ArrayQueueADT stack) {
        while (!ArrayQueueADT.isEmpty(stack)) {
            System.out.println(ArrayQueueADT.size(stack) + " " +
                    ArrayQueueADT.element(stack) + " " + ArrayQueueADT.dequeue(stack));
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT stack = new ArrayQueueADT();
        fill(stack);
        dump(stack);
    }
}
