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
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        queue.set(rear, data);
        rear = (rear + 1) % capacity;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = queue.get(front);
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return queue.get(front);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(queue.get((front + i) % capacity));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}