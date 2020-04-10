package queue;

public class ArrayQueue {
    private int size = 0, capacity = 5, head = 0, tail = 0;
    private Object[] e = new Object[capacity];

    // INV: contains [e1, e2, ..., en] \forall i in 1..n, ei != null
    // or empty
    // methods: enqueue, dequeue, element,
    // size, isEmpty, clear, push, peek, get, set

    //PRE: mod > 0
    private int shift(int x, int shift, int mod) {
        return ((x + shift) % mod + mod) % mod;
    }
    //POST: R = (x + shift) % mod
    // e' = e

    //PRE: true
    private void ensureCapacity() {
        Object[] nw = new Object[this.capacity *= 2];
        for (int i = 0; i < this.size; i++) {
            nw[i] = this.e[shift(i, this.head, this.size)];
        }
        this.e = nw;
        this.head = 0;
        this.tail = this.size;
    }
    //POST: e' = e

    // PRE: x != null
    // POST: e' = [e1, ..., en, x]
    public void enqueue(Object x) {
        assert(x != null);
        if (this.capacity == this.size) {
            ensureCapacity();
        }
        this.e[this.tail] = x;
        this.tail = shift(this.tail, 1, this.capacity);
        this.size++;
    }

    // PRE: x != null
    public void push(Object x) {
        assert(x != null);
        if (this.capacity == this.size) {
            ensureCapacity();
        }
        this.head = shift(this.head, -1, this.capacity);
        this.e[this.head] = x;
        this.size++;
    }
    // POST: [e1, ..., en] -> [x, e1, ..., en] = [e1', e2', ..., e(n+1)']
    // or
    // empty -> [x] = [e1']

    // PRE: not empty
    public Object element() {
        assert(this.size > 0);
        return this.e[this.head];
    }
    // POST: e' = e
    // RET = en

    // PRE: not empty
    public Object peek() {
        assert(this.size > 0);
        return this.e[shift(this.tail, -1, this.capacity)];
    }
    // POST: e' = e
    // RET = e1

    // PRE: not empty
    public Object dequeue() {
        assert(this.size > 0);
        Object ret = this.e[this.head];
        this.e[this.head] = null;
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
        Object ret = this.e[this.tail];
        this.e[this.tail] = null;
        this.size--;
        return ret;
    }
    // POST: [e1, ..., en] -> [e1, ..., e(n-1)] = [e1', ...,e(n-1)']
    // RET = en

    // PRE: index in [0, n - 1]
    public Object get(int index) {
        return this.e[shift(this.head, index, this.capacity)];
    }
    // POST: R = e(index + 1)
    // e' = e

    // PRE: index in [0, n - 1]
    public void set(int index, Object x) {
        this.e[shift(this.head, index, this.capacity)] = x;
    }
    // POST: e' = e except e(index + 1) -> x -> e(index + 1)'
    //

    // PRE: true
    public int size() {
        return this.size;
    }
    // POST: e' = e
    // RET = n

    // PRE: true
    public boolean isEmpty() {
        return this.size == 0;
    }
    // POST: e' = e
    // RET = (n == 0)

    // PRE: true
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.e[shift(i, this.head, this.capacity)] = null;
        }
        this.e = new Object[this.capacity = 5];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    // POST:
    // [e1, e2, ..., en] -> empty
}
