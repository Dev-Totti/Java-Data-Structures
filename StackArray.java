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
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void push(T data) {
        stack.set(++top, data);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.remove(top--);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.get(top);
    }

    public String toString() {
        return stack.toString();
    }

}