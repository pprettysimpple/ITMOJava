public abstract class AbstractQueue {
    protected int size = 0;

    protected abstract void implEnqueue(Object x);
    protected abstract Object implDequeue();

    public void enqueue(Object x) {
        assert(x != null);
        implEnqueue(x);
        size++;
    }

    public Object dequeue() {
        assert(!isEmpty());
        Object ret = implDequeue();
        size--;
        return ret;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }
}
