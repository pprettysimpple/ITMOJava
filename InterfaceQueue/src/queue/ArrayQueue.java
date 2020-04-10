package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] q = new Object[5];
    private int head = 0, tail = 0;

    private int shift(int i, int x) {
        return ((i + x) % q.length + q.length) % q.length;
    }

    private void ensureCap() {
        Object[] nq = new Object[2 * size];
        for (int i = 0; i < size; i++) {
            nq[i] = q[shift(i, head)];
        }
        head = 0;
        tail = size;
        q = nq;
    }

    @Override
    protected void implEnqueue(Object x) {
        if (size + 1 >= q.length) {
            ensureCap();
        }
        q[tail] = x;
        tail = shift(tail, 1);
    }

    @Override
    protected Object implDequeue() {
        Object ret = q[head];
        head = shift(head, 1);
        return ret;
    }

    @Override
    protected Object implElement() {
        return q[head];
    }
}
