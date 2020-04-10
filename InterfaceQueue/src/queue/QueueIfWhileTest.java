package queue;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class QueueIfWhileTest extends QueueTest<QueueIfWhileTest.QueueIfWhile> {
    public QueueIfWhileTest() {
        super(QueueIfWhile.class, ReferenceQueueIfWhile::new);
    }

    public static void main(final String[] args) {
        new QueueIfWhileTest().test();
    }

    @Override
    protected List<QueueIfWhile> linearTest(final QueueIfWhile queue) {
        final Predicate<Object> predicate = new Predicate<>() {
            final Object element = randomElement();

            @Override
            public boolean test(final Object o) {
                return o == element;
            }

            @Override
            public String toString() {
                return "== " + element;
            }
        };

        switch (random.nextInt(4)) {
            case 0:
                queue.removeIf(predicate);
                break;
            case 1:
                queue.retainIf(predicate);
                break;
            case 2:
                queue.takeWhile(predicate);
                break;
            case 3:
                queue.dropWhile(predicate);
                break;

        }
        return List.of();
    }

    public interface QueueIfWhile extends Queue {
        void removeIf(Predicate<Object> p);
        void retainIf(Predicate<Object> p);
        void dropWhile(Predicate<Object> p);
        void takeWhile(Predicate<Object> p);
    }

    protected static class ReferenceQueueIfWhile extends ReferenceQueue implements QueueIfWhile {
        public ReferenceQueueIfWhile(final Stream<Object> elements) {
            super(elements);
        }

        @Override
        public void removeIf(final Predicate<Object> p) {
            deque.removeIf(p);
        }

        @Override
        public void retainIf(final Predicate<Object> p) {
            deque.removeIf(Predicate.not(p));
        }

        @Override
        public void dropWhile(final Predicate<Object> p) {
            final boolean[] remove = {true};
            deque.removeIf(e -> remove[0] &= p.test(e));
        }

        @Override
        public void takeWhile(final Predicate<Object> p) {
            final boolean[] keep = {true};
            deque.removeIf(e -> !(keep[0] &= p.test(e)));
        }
    }
}
