package it.unimi.di.sweng;

import org.jetbrains.annotations.NotNull;

public class DoublyLinkedList<T> {

    public static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data)
        {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node<T> head;
    Node<T> tail;

    public DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
    }

    public void unshift(@NotNull T data) {
        Node<T> temp = new Node<T>(data);
        if (isEmpty()) {
            head = temp;
            tail = temp;
        }
        else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
    }

    public void push(@NotNull T data)
    {
        Node<T> temp = new Node<T>(data);
        if (isEmpty()) {
            head = temp;
            tail = temp;
        }
        else {
            temp.next = null;
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }
    }

    public T shift() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty List: illegal shift operation");
        }

        if (isSingle()) {
            T data = tail.data;
            tail = null;
            head = null;
            return data;
        }

        Node<T> temp = head;
        assert(head != null);
        head = head.next;
        head.prev = null;
        return temp.data;
    }

    public T pop()
    {
        if (isEmpty()) {
            throw new IllegalStateException("Empty List: illegal pop operation");
        }

        if (isSingle()) {
            T data = head.data;
            tail = null;
            head = null;
            return data;
        }

        Node<T> temp = tail;
        tail = temp.prev;
        tail.next = null;
        return temp.data;
    }

    private boolean isEmpty() {
        return head == null && tail == null;
    }

    private boolean isSingle() {
        return head == tail && head != null;
    }

}
