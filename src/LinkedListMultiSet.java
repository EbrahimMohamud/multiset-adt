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

    public LinkedListMultiSet() {
        this.head = null;
        this.size = 0;
    }

    @Override
    void add(Integer item){
        Node newNode = new Node(item);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }
    @Override
    void remove(Integer item){
        Node currentNode = this.head;
        Node previousNode = null;
        while (currentNode != null) {
            if (currentNode.item == item) {
                this.size--;
                if (previousNode != null) {
                    previousNode.next = currentNode.next;
                } else {
                    this.head = currentNode.next;
                }
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    }
    @Override
    boolean contains(Integer item) {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.item == item) {
                return true;
            }
        } currentNode = currentNode.next;
        return false;
    }
    @Override
    boolean isEmpty(){
        return this.head == null;
    }
    @Override
    int count(Integer item){
        Node currentNode = this.head;
        int count = 0;
        while (currentNode != null) {
            if (currentNode.item == item) {
                count++;
            } currentNode = currentNode.next;
        }
        return count;
    }
    @Override
    int size() {
        return this.size;
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
