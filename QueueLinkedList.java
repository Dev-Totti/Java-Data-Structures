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
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data) {
        queue.add(data);
        size++;
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

    @Override
    public String toString() {
        return queue.toString();
    }

}