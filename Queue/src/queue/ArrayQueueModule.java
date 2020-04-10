package queue;

public class ArrayQueueModule {
    private static int size = 0, capacity = 5, head = 0, tail = 0;
    private static Object[] e = new Object[capacity];

    // INV: contains [e1, e2, ..., en] \forall i in 1..n, ei != null
    // or empty
    // methods: enqueue, dequeue, element,
    // size, isEmpty, clear, push, peek, get, set

    private static int shift(int x, int shift, int mod) {
        return ((x + shift) % mod + mod) % mod;
    }

    private static void ensureCapacity() {
        Object[] nw = new Object[capacity *= 2];
        for (int i = 0; i < size; i++) {
            nw[i] = e[shift(i, head, size)];
        }
        e = nw;
        head = 0;
        tail = size;
    }

    // PRE: x != null
    public static void enqueue(Object x) {
        assert(x != null);
        if (capacity == size) {
            ensureCapacity();
        }
        e[tail] = x;
        tail = shift(tail, 1, capacity);
        size++;
    }
    // POST: [e1, ..., en] -> [e1, ..., en, x] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: x != null
    public static void push(Object x) {
        assert(x != null);
        if (capacity == size) {
            ensureCapacity();
        }
        head = shift(head, -1, capacity);
        e[head] = x;
        size++;
    }
    // POST: [e1, ..., en] -> [x, e1, ..., en] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: not empty
    public static Object element() {
        assert(size > 0);
        return e[head];
    }
    // RET = en

    // PRE: not empty
    public static Object peek() {
        assert(size > 0);
        return e[shift(tail, -1, capacity)];
    }
    // RET = e1

    // PRE: not empty
    public static Object dequeue() {
        assert(size > 0);
        Object ret = e[head];
        e[head] = null;
        head = shift(head, 1, capacity);
        size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e2, ..., en] = [e1', ...,e(n-1)']
    // RET = e1

    // PRE: not empty
    public static Object remove() {
        assert(size > 0);
        tail = shift(tail, -1, capacity);
        Object ret = e[tail];
        e[tail] = null;
        size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e1, ..., e(n-1)] = [e1', ...,e(n-1)']
    // RET = en

    // PRE: index in [0, n - 1]
    public static Object get(int index) {
        return e[shift(head, index, capacity)];
    }
    // POST: R = e(index + 1)

    // PRE: index in [0, n - 1]
    public static void set(int index, Object x) {
        e[shift(head, index, capacity)] = x;
    }
    // POST: e(index + 1) -> x -> e(index + 1)'

    // PRE: true
    public static int size() {
        return size;
    }
    // RET = n

    // PRE: true
    public static boolean isEmpty() {
        return size == 0;
    }
    // RET = (n == 0)

    // PRE: true
    public static void clear() {
        for (int i = 0; i < size; i++) {
            e[shift(i, head, capacity)] = null;
        }
        e = new Object[capacity = 5];
        head = 0;
        tail = 0;
        size = 0;
    }
    // POST: empty -> empty
    // or
    // [e1, e2, ..., en] -> empty
}
