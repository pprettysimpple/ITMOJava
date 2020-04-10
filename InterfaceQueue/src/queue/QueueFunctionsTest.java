package queue;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class QueueFunctionsTest extends QueueTest<QueueFunctionsTest.QueueFunctions> {
    public QueueFunctionsTest() {
        super(QueueFunctions.class, ReferenceQueueFunctions::new);
    }

    public static void main(final String[] args) {
        new QueueFunctionsTest().test();
    }

    @Override
    protected List<QueueFunctions> linearTest(final QueueFunctions queue) {
        if (random.nextBoolean()) {
            return List.of(queue.filter(Predicate.isEqual(randomElement())));
        } else {
            return List.of(queue.map(random.nextBoolean() ? String::valueOf : Object::hashCode));
        }
    }

    protected interface QueueFunctions extends Queue {
        QueueFunctions filter(final Predicate<Object> p);
        QueueFunctions map(final Function<Object, Object> f);
    }

    protected static class ReferenceQueueFunctions extends ReferenceQueue implements QueueFunctions {
        public ReferenceQueueFunctions(final Stream<Object> elements) {
            super(elements);
        }

        @Override
        public QueueFunctions filter(final Predicate<Object> p) {
            return new ReferenceQueueFunctions(deque.stream().filter(p));
        }

        @Override
        public QueueFunctions map(final Function<Object, Object> f) {
            return new ReferenceQueueFunctions(deque.stream().map(f));
        }
    }
}
