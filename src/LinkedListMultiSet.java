/**
 * Unlike the TreeMultiList, this implementation does not just "wrap" an
 * underlying tree, it is instead a custom LinkedList implementation, which
 * only provides the necessary MultiSet methods.
 *
 * ==========  Representation Invariant: ==========
 * this.front is None represents an empty MultiSet  */

public class LinkedListMultiSet extends MultiSet {
    private Node head;
    private int size;

    @Override
    void add(Integer item){
    }
    @Override
    void remove(Integer item){
    }
    @Override
    boolean contains(Integer item) {
        return false;
    }
    @Override
    boolean isEmpty(){
        return true;
    }
    @Override
    int count(Integer item){
        return 0;
    }
    @Override
    int size() {
        return 0;
    }
}

class Node {
    int item;
    Node next;

    Node(int item) {
        this.item = item;
        this.next = null;
    }
}
