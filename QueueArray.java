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
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(T data) {
        this.queue.set(this.size++, data);
    }

    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        this.size--;
        return this.queue.remove(0);
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.queue.get(0);
    }

    public String toString() {
        return this.queue.toString();
    }

}