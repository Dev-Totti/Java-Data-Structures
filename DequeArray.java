public class DequeArray<T> {
    // Attributes
    private ArrayDynamic<T> deque;
    private int size;
    private int capacity;

    // Constructors
    public DequeArray() {
        this.capacity = 10;
        this.deque = new ArrayDynamic<T>(this.capacity);
        this.size = 0;
    }

    public DequeArray(int capacity) {
        this.capacity = capacity;
        this.deque = new ArrayDynamic<T>(this.capacity);
        this.size = 0;
    }

    // Methods
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(T data) {
        this.deque.prepend(data);
        this.size++;
    }

    public void addLast(T data) {
        this.deque.add(data);
        this.size++;
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        this.size--;
        return this.deque.remove(0);
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        this.size--;
        return this.deque.remove(this.size);
    }

    public T peekFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return this.deque.get(0);
    }

    public T peekLast() {
        if (this.isEmpty()) {
            return null;
        }
        return this.deque.get(this.size - 1);
    }

    public String toString() {
        return this.deque.toString();
    }

}