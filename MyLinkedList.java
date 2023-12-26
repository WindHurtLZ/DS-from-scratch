import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    // double linked list node class
    private static class Node<E> {
        E val;

        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.size = 0;
        head.next = tail;
        tail.prev = head;
    }

    /**** Create(Add) ****/
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);

        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;

//      Or we can create a temp pointer to locate
//      head's next. A more readable implementation:

//      Node<E> temp = head.next;
//      newNode.next = temp;
//      newNode.prev = temp;
//      head.next = newNode;
//      temp.prev = newNode;

        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;

//      Or we can create a temp pointer to locate
//      head's next. A more readable implementation:

//      Node<E> temp = tail.prev;
//      newNode.next = temp;
//      newNode.prev = temp;
//      tail.prev = newNode;
//      temp.next = newNode;

        size++;
    }

    public void add(int index, E e) {
        checkPositionIndex(index);

        Node<E> newNode = new Node<>(e);
        Node<E> oriNode = getNode(index);

        newNode.next = oriNode;
        newNode.prev = oriNode.prev;
        oriNode.prev.next = newNode;
        oriNode.prev = newNode;

        size++;
    }

    /**** Update ****/
    public E set(int index, E e) {
        checkElementIndex(index);

        Node<E> p = getNode(index);
        E oldVal = p.val;
        p.val = e;

        return oldVal;
    }

    /**** Retrieve ****/
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).val;
    }

    /**** Delete ****/
    // For delete, we only need change the next and prev reference
    // the GC in JAVA will clean up object that no reference point to.
    // For doubly linked lists, deletion is O(1) if the node is known,
    // while for singly linked lists, you usually need O(n) to find the previous node
    // (unless it's the first node). For unknown nodes, both types require O(n) to locate the node.
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E oldVal = head.next.val;
        head.next = head.next.next;
        head.next.prev = head;

        // more readable way
//        Node<E> oldNode = head.next;
//        Node<E> temp = oldNode.next;
//        // head -> oldNode -> temp
//        head.next = temp;
//        temp.prev = head;
//        oldNode.prev = null;
//        oldNode.next = null;

        size--;
        return oldVal;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E oldVal = tail.prev.val;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        // more readable way
//        Node<E> oldNode = head.next;
//        Node<E> temp = oldNode.next;
//        // head -> oldNode -> temp
//        head.next = temp;
//        temp.prev = head;
//        oldNode.prev = null;
//        oldNode.next = null;

        size--;
        return oldVal;
    }

    public E remove(int index) {
        checkElementIndex(index);

        Node<E> oldNode = getNode(index);

        oldNode.prev.next = oldNode.next;
        oldNode.next.prev = oldNode.prev;

        size--;
        return oldNode.val;
    }

    // Public Tool method
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Checker and private tool method
    private Node<E> getNode(int index) {
        Node<E> iterator = head.next;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }
        return iterator;
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }
}
