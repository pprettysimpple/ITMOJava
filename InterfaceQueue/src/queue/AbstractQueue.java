package queue;

import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    protected abstract void implEnqueue(Object x);
    protected abstract Object implDequeue();
    protected abstract Object implElement();

    @Override
    public void enqueue(Object x) {
        assert(x != null);
        implEnqueue(x);
        size++;
    }

    @Override
    public Object dequeue() {
        assert(!isEmpty());
        Object ret = implDequeue();
        size--;
        return ret;
    }

    @Override
    public Object element() {
        assert(!isEmpty());
        return implElement();
    }

    @Override
    public void retainIf(Predicate<Object> predicate) {
        int n = size;
        Object cur;
        for (int i = 0; i < n; i++) {
            if (predicate.test(cur = this.element())) {
                this.enqueue(cur);
            }
            this.dequeue();
        }
    }

    @Override
    public void removeIf(Predicate<Object> predicate) {
        retainIf(predicate.negate());
    }

    private int processFirst(Predicate<Object> predicate, boolean save) {
        int countLeft = size;
        while (countLeft > 0 && predicate.test(this.element())) {
            countLeft--;
            if (save) {
                this.enqueue(this.dequeue());
            } else {
                this.dequeue();
            }
        }
        return countLeft;
    }

    @Override
    public void takeWhile(Predicate<Object> predicate) {
        int countLeft = processFirst(predicate, true);
        for (int i = 0; i < countLeft; i++) {
            this.dequeue();
        }
    }

    @Override
    public void dropWhile(Predicate<Object> predicate) {
        processFirst(predicate, false);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.dequeue();
        }
    }
}
