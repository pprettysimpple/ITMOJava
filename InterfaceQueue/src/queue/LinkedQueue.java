public class LinkedQueue extends AbstractQueue {
    private Node head = null, tail = null;

    @Override
    protected void implEnqueue(Object x) {
        tail = new Node(new Node(x), tail);
    }

    @Override
    protected Object implDequeue() {
        Object ret = tail;
        tail = tail.prev;
        return ret;
    }

    static class Node {
        Node prev;
        Object val;

        public Node(Object x) {
            this.val = x;
        }

        public Node(Object nw, Node last) {
            this.val = nw;
            this.prev = last;
        }
    }
}
