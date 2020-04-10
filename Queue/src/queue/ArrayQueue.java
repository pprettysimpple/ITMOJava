package queue;

public class ArrayQueue {
    private int size = 0, capacity = 5, head = 0, tail = 0;
    private Object[] q = new Object[capacity];

    // INV: contains [e1, e2, ..., en] or empty
    // methods: enqueue, dequeue, element,
    // size, isEmpty, clear, push, peek, get, set

    private int shift(int x, int shift, int mod) {
        return ((x + shift) % mod + mod) % mod;
    }

    private void ensureCapacity() {
        Object[] nw = new Object[this.capacity *= 2];
        for (int i = 0; i < this.size; i++) {
            nw[i] = this.q[shift(i, this.head, this.size)];
        }
        this.q = nw;
        this.head = 0;
        this.tail = this.size;
    }

    // PRE: x != null
    public void enqueue(Object x) {
        assert(x != null);
        if (this.capacity == this.size) {
            ensureCapacity();
        }
        this.q[this.tail] = x;
        this.tail = shift(this.tail, 1, this.capacity);
        this.size++;
    }
    // POST: [e1, ..., en] -> [e1, ..., en, x] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: x != null
    public void push(Object x) {
        assert(x != null);
        if (this.capacity == this.size) {
            ensureCapacity();
        }
        this.head = shift(this.head, -1, this.capacity);
        this.q[this.head] = x;
        this.size++;
    }
    // POST: [e1, ..., en] -> [x, e1, ..., en] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: not empty
    public Object element() {
        assert(this.size > 0);
        return this.q[this.head];
    }
    // RET = en

    // PRE: not empty
    public Object peek() {
        assert(this.size > 0);
        return this.q[shift(this.tail, -1, this.capacity)];
    }
    // RET = e1

    // PRE: not empty
    public Object dequeue() {
        assert(this.size > 0);
        Object ret = this.q[this.head];
        this.q[this.head] = null;
        this.head = shift(this.head, 1, this.capacity);
        this.size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e2, ..., en] = [e1', ...,e(n-1)']
    // RET = e1

    // PRE: not empty
    public Object remove() {
        assert(this.size > 0);
        this.tail = shift(this.tail, -1, this.capacity);
        Object ret = this.q[this.tail];
        this.q[this.tail] = null;
        this.size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e1, ..., e(n-1)] = [e1', ...,e(n-1)']
    // RET = en

    // PRE: index in [0, n - 1]
    public Object get(int index) {
        return this.q[shift(this.head, index, this.capacity)];
    }
    // POST: R = e(index + 1)

    // PRE: index in [0, n - 1]
    public void set(int index, Object x) {
        this.q[shift(this.head, index, this.capacity)] = x;
    }
    // POST: e(index + 1) -> x -> e(index + 1)'

    // PRE: true
    public int size() {
        return this.size;
    }
    // RET = n

    // PRE: true
    public boolean isEmpty() {
        return this.size == 0;
    }
    // RET = (n == 0)

    // PRE: true
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.q[shift(i, this.head, this.capacity)] = null;
        }
        this.q = new Object[this.capacity = 5];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    // POST: empty -> empty
    // or
    // [e1, e2, ..., en] -> empty
}
