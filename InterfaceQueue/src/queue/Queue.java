public interface Queue {
    void enqueue(Object x);

    Object dequeue();

    Object element();

    int size();

    boolean isEmpty();

    void clear();
}
