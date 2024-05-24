public class LinkedListDouble<T> {
    // Node - Inner Class
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        // Node - Constructors
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        // Node - Getters
        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        // Node - Setters
        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    // Attributes
    private Node head;
    private Node tail;
    private int size;

    // Constructors
    public LinkedListDouble() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedListDouble(T data) {
        this.head = new Node(data);
        this.tail = this.head;
        this.size = 1;
    }

    // Methods
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public LinkedListDouble<T> getReversed() {
        LinkedListDouble<T> reversed = new LinkedListDouble<>();
        Node current = tail;
        while (current != null) {
            reversed.append(current.getData());
            current = current.getPrev();
        }
        return reversed;
    }

    public void reverse() {
        if (isEmpty() || size == 1) {
            return;
        }
        Node temp = null;
        Node current = head;
        while (current != null) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public void prepend(T data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        if (head != null) {
            head.setPrev(newNode);
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    public void append(T data) {
        Node newNode = new Node(data);
        newNode.setPrev(tail);
        if (tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(T data) {
        append(data);
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            System.out.println("Index out of bounds");
            return;
        }
        if (index == 0) {
            prepend(data);
            return;
        }
        if (index == size) {
            append(data);
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        Node newNode = new Node(data);
        newNode.setNext(current);
        newNode.setPrev(current.getPrev());
        current.getPrev().setNext(newNode);
        current.setPrev(newNode);
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        T removed = head.getData();
        head = head.getNext();
        if (head != null) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        size--;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        T removed = tail.getData();
        tail = tail.getPrev();
        if (tail != null) {
            tail.setNext(null);
        } else {
            head = null;
        }
        size--;
        return removed;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        T removed = current.getData();
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        size--;
        return removed;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

}