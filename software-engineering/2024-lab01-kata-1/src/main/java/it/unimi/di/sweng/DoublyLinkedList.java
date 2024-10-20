package it.unimi.di.sweng;

public class DoublyLinkedList<T> {

    static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private final T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> tail;
    private Node<T> head;

    public void unshift(T element) {
        Node<T> node = new Node<>(element);

        if (head == null) {
            head = node;
        }

        if (tail == null) {
            tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    public void push(T element) {
        Node<T> node = new Node<>(element);

        if (head == null) {
            head = node;
        }

        if (tail == null) {
            tail = node;
            return;
        }

        if (head.next == null) {
            head.next = node;
        }

        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    public T shift() {
        if (head == null) {
            throw new IllegalStateException("Empty List: illegal shift operation");
        }
        T data = head.data;
        head = head.next;
        return data;
    }

    public T pop() {
        if (tail == null) {
            throw new IllegalStateException("Empty List: illegal pop operation");
        }
        T data = tail.data;
        tail = tail.prev;
        return data;
    }
}
