package queue;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ArrayQueueDequeTest<T extends ArrayQueueDequeTest.QueueDeque> extends ArrayQueueTest<T> {
    public ArrayQueueDequeTest(final Class<T> type, final Function<Stream<Object>, T> reference) {
        super(type, reference);
    }

    public static void main(final String[] args) {
        new ArrayQueueDequeTest<>(QueueDeque.class, ReferenceQueueDeque::new).test();
    }

    @Override
    protected void add(final T queue, final Object element) {
        if (random.nextBoolean()) {
            super.add(queue, element);
        } else {
            queue.push(element);
        }
    }

    @Override
    protected void check(final T queue) {
        if (random.nextBoolean()) {
            super.check(queue);
        } else {
            queue.peek();
        }
    }

    @Override
    protected void remove(final T queue) {
        if (random.nextBoolean()) {
            super.remove(queue);
        } else {
            queue.remove();
        }
    }

    protected interface QueueDeque extends Queue {
        void push(final Object element);
        Object peek();
        Object remove();
    }

    protected static class ReferenceQueueDeque extends ReferenceQueue implements QueueDeque {
        public ReferenceQueueDeque(final Stream<Object> elements) {
            super(elements);
        }

        @Override
        public void push(final Object element) {
            deque.addFirst(element);
        }

        @Override
        public Object peek() {
            return deque.getLast();
        }

        @Override
        public Object remove() {
            return deque.removeLast();
        }
    }
}
