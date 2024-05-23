public class QueueArrayCircular<T> {
    // Attributes
    private ArrayStatic<T> queue;
    private int size;
    private int capacity;
    private int front;
    private int rear;

    // Constructors
    public QueueArrayCircular() {
        this.capacity = 10;
        this.queue = new ArrayStatic<T>(this.capacity);
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public QueueArrayCircular(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayStatic<T>(this.capacity);
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // Methods
    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(T data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        this.queue.set(this.rear, data);
        this.rear = (this.rear + 1) % this.capacity;
        this.size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = this.queue.get(this.front);
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return this.queue.get(this.front);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.queue.get((this.front + i) % this.capacity));
            if (i < this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}