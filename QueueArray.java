public class QueueArray<T> {
    // Attributes
    private ArrayDynamic<T> queue;
    private int size;
    private int capacity;

    // Constructors
    public QueueArray() {
        this.capacity = 10;
        this.queue = new ArrayDynamic<T>(this.capacity);
        this.size = 0;
    }

    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDynamic<T>(this.capacity);
        this.size = 0;
    }

    // Methods
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data) {
        queue.set(size++, data);
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        size--;
        return queue.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

    public String toString() {
        return queue.toString();
    }

}