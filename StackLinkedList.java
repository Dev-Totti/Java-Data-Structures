public class StackLinkedList<T> {
    // Attributes
    private LinkedListDouble<T> stack;
    private int top;

    // Constructors
    public StackLinkedList() {
        this.stack = new LinkedListDouble<>();
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
        this.stack.add(data);
        this.top++;
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

    @Override
    public String toString() {
        return this.stack.toString();
    }

}