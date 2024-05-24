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
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        deque.prepend(data);
        size++;
    }

    public void addLast(T data) {
        deque.add(data);
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        return deque.remove(0);
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        return deque.remove(size);
    }

    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return deque.get(0);
    }

    public T peekLast() {
        if (isEmpty()) {
            return null;
        }
        return deque.get(size - 1);
    }

    public String toString() {
        return deque.toString();
    }

}