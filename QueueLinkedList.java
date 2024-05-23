public class QueueLinkedList<T> {
    // Attributes
    private LinkedListDouble<T> queue;
    private int size;

    // Constructors
    public QueueLinkedList() {
        this.queue = new LinkedListDouble<>();
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
        this.queue.add(data);
        this.size++;
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

    @Override
    public String toString() {
        return this.queue.toString();
    }

}