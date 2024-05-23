public class LinkedListSingle<T> {
    // Node - Inner Class
    private class Node {
        private T data;
        private Node next;

        // Node - Constructors
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        // Node - Getters
        public T getData() {
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }

        // Node - Setters
        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    // Attributes
    private Node head;
    private int size;

    // Constructors
    public LinkedListSingle() {
        this.head = null;
        this.size = 0;
    }

    public LinkedListSingle(T data) {
        this.head = new Node(data);
        this.size = 1;
    }

    // Methods
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public LinkedListSingle<T> getReversed() {
        LinkedListSingle<T> reversed = new LinkedListSingle<>();
        Node current = this.head;
        while (current != null) {
            reversed.prepend(current.getData());
            current = current.getNext();
        }
        return reversed;
    }

    public void reverse() {
        if (isEmpty() || size == 1) {
            return;
        }
        Node prev = null;
        Node current = this.head;
        Node next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public void prepend(T data) {
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    public void append(T data) {
        if (isEmpty()) {
            prepend(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        this.size++;
    }

    public void add(T data) {
        append(data);
    }

    public void insert(T data, int index) {
        if (index < 0 || index > size) {
            System.out.println("Index out of bounds");
            return;
        }
        if (index == 0) {
            prepend(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        this.size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        T removed = this.head.getData();
        this.head = this.head.getNext();
        this.size--;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        Node current = this.head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T removed = current.getNext().getData();
        current.setNext(null);
        this.size--;
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
        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        T removed = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        this.size--;
        return removed;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        Node current = this.head;
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
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = this.head;
        while (current.getNext() != null) {
            sb.append(current.getData());
            sb.append(", ");
            current = current.getNext();
        }
        sb.append(current.getData());
        sb.append("]");
        return sb.toString();
    }

}