package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head = null, tail = null;

    @Override
    protected void implEnqueue(Object x) {
        if (isEmpty()) {
            head = tail = new Node(x);
            return;
        }
        tail = tail.next = new Node(x);
    }

    @Override
    protected Object implDequeue() {
        Object ret = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return ret;
    }

    @Override
    protected Object implElement() {
        return head.val;
    }

    static class Node {
        Node next;
        Object val;

        public Node(Object nw) {
            this.val = nw;
        }
    }
}
