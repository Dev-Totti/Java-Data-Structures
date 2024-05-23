public class StackArray<T> {
    // Attributes
    private ArrayDynamic<T> stack;
    private int top;
    private int capacity;

    // Constructors
    public StackArray() {
        this.capacity = 10;
        this.stack = new ArrayDynamic<T>(this.capacity);
        this.top = -1;
    }

    public StackArray(int capacity) {
        this.capacity = capacity;
        this.stack = new ArrayDynamic<T>(this.capacity);
        this.top = -1;
    }

    // Methods
    public boolean isEmpty() {
        return this.top == -1;
    }

    public int size() {
        return this.top + 1;
    }

    public void push(T data) {
        this.stack.set(++this.top, data);
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.remove(this.top--);
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.get(this.top);
    }

    public String toString() {
        return this.stack.toString();
    }

}