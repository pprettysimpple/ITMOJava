package queue;

public class ArrayQueueADT {
    private int size = 0, capacity = 5, head = 0, tail = 0;
    private Object[] q = new Object[capacity];

    // INV: contains [e1, e2, ..., en] or empty
    // methods: enqueue, dequeue, element,
    // size, isEmpty, clear, push, peek, get, set

    private static int shift(ArrayQueueADT self, int x, int shift, int mod) {
        return ((x + shift) % mod + mod) % mod;
    }

    private static void ensureCapacity(ArrayQueueADT self) {
        Object[] nw = new Object[self.capacity *= 2];
        for (int i = 0; i < self.size; i++) {
            nw[i] = self.q[shift(self, i, self.head, self.size)];
        }
        self.q = nw;
        self.head = 0;
        self.tail = self.size;
    }

    // PRE: x != null
    public static void enqueue(ArrayQueueADT self, Object x) {
        assert(x != null);
        if (self.capacity == self.size) {
            ensureCapacity(self);
        }
        self.q[self.tail] = x;
        self.tail = shift(self, self.tail, 1, self.capacity);
        self.size++;
    }
    // POST: [e1, ..., en] -> [e1, ..., en, x] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: x != null
    public static void push(ArrayQueueADT self, Object x) {
        assert(x != null);
        if (self.capacity == self.size) {
            ensureCapacity(self);
        }
        self.head = shift(self, self.head, -1, self.capacity);
        self.q[self.head] = x;
        self.size++;
    }
    // POST: [e1, ..., en] -> [x, e1, ..., en] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: not empty
    public static Object element(ArrayQueueADT self) {
        assert(self.size > 0);
        return self.q[self.head];
    }
    // RET = en

    // PRE: not empty
    public static Object peek(ArrayQueueADT self) {
        assert(self.size > 0);
        return self.q[shift(self, self.tail, -1, self.capacity)];
    }
    // RET = e1

    // PRE: not empty
    public static Object dequeue(ArrayQueueADT self) {
        assert(self.size > 0);
        Object ret = self.q[self.head];
        self.q[self.head] = null;
        self.head = shift(self, self.head, 1, self.capacity);
        self.size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e2, ..., en] = [e1', ...,e(n-1)']
    // RET = e1

    // PRE: not empty
    public static Object remove(ArrayQueueADT self) {
        assert(self.size > 0);
        self.tail = shift(self, self.tail, -1, self.capacity);
        Object ret = self.q[self.tail];
        self.q[self.tail] = null;
        self.size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e1, ..., e(n-1)] = [e1', ...,e(n-1)']
    // RET = en

    // PRE: index in [0, n - 1]
    public static Object get(ArrayQueueADT self, int index) {
        return self.q[shift(self, self.head, index, self.capacity)];
    }
    // POST: R = e(index + 1)

    // PRE: index in [0, n - 1]
    public static void set(ArrayQueueADT self, int index, Object x) {
        self.q[shift(self, self.head, index, self.capacity)] = x;
    }
    // POST: e(index + 1) -> x -> e(index + 1)'

    // PRE: true
    public static int size(ArrayQueueADT self) {
        return self.size;
    }
    // RET = n

    // PRE: true
    public static boolean isEmpty(ArrayQueueADT self) {
        return self.size == 0;
    }
    // RET = (n == 0)

    // PRE: true
    public static void clear(ArrayQueueADT self) {
        for (int i = 0; i < self.size; i++) {
            self.q[shift(self, i, self.head, self.capacity)] = null;
        }
        self.q = new Object[self.capacity = 5];
        self.head = 0;
        self.tail = 0;
        self.size = 0;
    }
    // POST: empty -> empty
    // or
    // [e1, e2, ..., en] -> empty
}
