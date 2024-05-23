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
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return this.prev;
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
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public LinkedListDouble<T> getReversed() {
        LinkedListDouble<T> reversed = new LinkedListDouble<>();
        Node current = this.tail;
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
        Node current = this.head;
        while (current != null) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        }
        temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    public void prepend(T data) {
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        if (this.head != null) {
            this.head.setPrev(newNode);
        } else {
            this.tail = newNode;
        }
        this.head = newNode;
        this.size++;
    }

    public void append(T data) {
        Node newNode = new Node(data);
        newNode.setPrev(this.tail);
        if (this.tail != null) {
            this.tail.setNext(newNode);
        } else {
            this.head = newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    public void add(T data) {
        append(data);
    }

    public void insert(int index, T data) {
        if (index < 0 || index > this.size) {
            System.out.println("Index out of bounds");
            return;
        }
        if (index == 0) {
            prepend(data);
            return;
        }
        if (index == this.size) {
            append(data);
            return;
        }
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        Node newNode = new Node(data);
        newNode.setNext(current);
        newNode.setPrev(current.getPrev());
        current.getPrev().setNext(newNode);
        current.setPrev(newNode);
        this.size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        T removed = this.head.getData();
        this.head = this.head.getNext();
        if (this.head != null) {
            this.head.setPrev(null);
        } else {
            this.tail = null;
        }
        this.size--;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        T removed = this.tail.getData();
        this.tail = this.tail.getPrev();
        if (this.tail != null) {
            this.tail.setNext(null);
        } else {
            this.head = null;
        }
        this.size--;
        return removed;
    }

    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            System.out.println("Index out of bounds");
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == this.size - 1) {
            return removeLast();
        }
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        T removed = current.getData();
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        this.size--;
        return removed;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
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
        if (index < 0 || index >= this.size) {
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
        this.tail = null;
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