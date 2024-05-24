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
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void push(T data) {
        stack.add(data);
        top++;
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

    @Override
    public String toString() {
        return stack.toString();
    }

}