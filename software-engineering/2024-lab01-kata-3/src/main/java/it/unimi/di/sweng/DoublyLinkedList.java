package it.unimi.di.sweng;

import java.util.function.Consumer;

public class DoublyLinkedList<T> {

    private static class Node<T> {
        private final T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private boolean isEmpty() {
        return head == null && tail == null;
    }

    public void unshift(T element) {
        action(element, (Node<T> current) -> {
            current.next = head;
            head.prev = current;
            head = current;
        });
    }

    public void push(T element) {
        action(element, (Node<T> current) -> {
            current.prev = tail;
            tail.next = current;
            tail = current;
        });
    }

    private void action(T element, Consumer<Node<T>> fn) {
        if (isEmpty()) {
            head = tail = new Node<>(element);
        } else {
            fn.accept(new Node<>(element));
        }
    }

    public T shift() {
        T element = head.element;
        head = head.next;
        return element;
    }

    public T pop() {
        T element = tail.element;
        tail = tail.prev;
        return element;
    }
}
