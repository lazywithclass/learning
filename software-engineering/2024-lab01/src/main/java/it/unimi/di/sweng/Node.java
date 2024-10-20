package it.unimi.di.sweng;

public class Node<T> {


        T data;
        Node prev;
        Node next;

        public Node(T data)
        {
            this.data = data;
            this.prev = null;
            this.next = null;
        }


}


   /* private final T element;
    private Node<T> next;
    private Node<T> previous;

    public Node(T element, Node<T> prev, Node<T> next) {
        this.element = element;
        this.previous = prev;
        this.next = next;
    }

    public void add(T element) {
        Node<T> n = new Node<T>(element, this, null);
        this.next = n;
    }

    public T pop() {
        previous.next = null;
        return element;
    }

    public boolean hasNext() {
        return next != null;
    }


    public Node<T> next() {
        return next;
    }*/

