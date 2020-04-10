package queue;

import java.util.function.Predicate;

public interface Queue {
    // INV: contains [e1, e2, ..., en] \forall i in 1..n, ei != null
    // or empty
    // methods: enqueue, dequeue, element,
    // size, isEmpty, clear, push, peek, get, set

    // PRE: x != null
    void enqueue(Object x);
    // POST: e' = [e1, ..., en, x]

    // PRE: not empty
    Object dequeue();
    // POST: e' = [e2, ..., en]
    // RET = e1

    // PRE: not empty
    Object element();
    // POST: e' = e
    // RET = en

    //PRE: true
    void removeIf(Predicate<Object> predicate);
    //POST: i = [i_1, i_2, ..., i_k] is subSeq of [1, ..., n] :
    // \forall k in 1..n: (k in i <=> predicate.test(e_k))
    // e' = [e_{i_1}, ..., e_{i_k}]

    //PRE: true
    void retainIf(Predicate<Object> predicate);
    //POST: i = [i_1, i_2, ..., i_k] is subSeq of [1, ..., n] :
    // \forall k in 1..n: (k in i <=> !predicate.test(e_k))
    // e' = [e_{i_1}, ..., e_{i_k}]

    //PRE: true
    void takeWhile(Predicate<Object> predicate);
    //POST:
    // \exists k in [0, ..., n] :
    // \forall i in [1, ..., k] predicate.test(e_i) == true
    // and
    // predicate.test(e_{k+1}) == false or k == n
    // [e_1, ..., e_k, e_{k+1}, ..., e_n] -> [e_1, ..., e_k] = e'

    //PRE: true
    void dropWhile(Predicate<Object> predicate);
    //POST:
    // /exists k in [0, ..., n] :
    // \forall i in [1, ..., k] predicate.test(e_i) == true
    // and
    // predicate.test(e_{k+1}) == false or k == n
    // [e_1, ..., e_k, e_{k+1}, ..., e_n] -> [e_{k + 1}, ..., e_n] = e'

    // PRE: true
    int size();
    // POST: e' = e
    // RET = n

    // PRE: true
    boolean isEmpty();
    // POST: e' = e
    // RET = (n == 0)

    // PRE: true
    void clear();
    // POST:
    // [e1, e2, ..., en] -> empty
}
