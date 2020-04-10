package queue;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ArrayQueueIndexedDequeTest<T extends ArrayQueueIndexedDequeTest.IndexedDeque> extends ArrayQueueDequeTest<T> {

    public ArrayQueueIndexedDequeTest(final Class<T> type, final Function<Stream<Object>, T> reference) {
        super(type, reference);
    }

    public static void main(final String[] args) {
        new ArrayQueueIndexedDequeTest<>(IndexedDeque.class, ReferenceIndexedDeque::new).test();
    }

    @Override
    protected void check(final T queue) {
        super.check(queue);
        queue.get(randomIndex(queue));
    }

    private int randomIndex(final T queue) {
        return random.nextInt(queue.size());
    }

    @Override
    protected void add(final T queue, final Object element) {
        if (queue.isEmpty() || random.nextBoolean()) {
            super.add(queue, element);
        } else {
            queue.set(randomIndex(queue), randomElement());
        }
    }

    protected interface IndexedDeque extends QueueDeque {
        Object get(int index);
        void set(int index, Object value);
    }

    protected static class ReferenceIndexedDeque extends ReferenceQueueDeque implements IndexedDeque {
        private static final Field ELEMENTS = getField("elements");
        private static final Field HEAD = getField("head");

        public ReferenceIndexedDeque(final Stream<Object> elements) {
            super(elements);
        }

        @Override
        public Object get(final int index) {
            final Object[] elements = get(ELEMENTS);
            final int head = get(HEAD);
            return elements[(head + index) % elements.length];
        }

        @Override
        public void set(final int index, final Object value) {
            final Object[] elements = get(ELEMENTS);
            final int head = get(HEAD);
            elements[(head + index) % elements.length] = value;
        }

        private static Field getField(final String name) {
            try {
                final Field field = ArrayDeque.class.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (final NoSuchFieldException e) {
                throw new AssertionError("Reflection error: " + e.getMessage(), e);
            }
        }

        @SuppressWarnings("unchecked")
        private <T> T get(final Field field) {
            try {
                return (T) field.get(deque);
            } catch (final IllegalAccessException e) {
                throw new AssertionError("Cannot access field " + field.getName() + ": " + e.getMessage(), e);
            }
        }
    }
}
