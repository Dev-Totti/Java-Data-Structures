public class ArrayStatic<T> {
    // Attributes
    private T[] array;
    private int size;
    private int capacity;

    // Constructors
    @SuppressWarnings("unchecked")
    public ArrayStatic() {
        this.size = 0;
        this.capacity = 10;
        this.array = (T[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayStatic(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
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

    public void prepend(T element) {
        if (isFull()) {
            System.out.println("Array is full");
            return;
        }
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = element;
        size++;
    }

    public void add(T element) {
        if (isFull()) {
            System.out.println("Array is full");
            return;
        }
        array[size++] = element;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        T element = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        return element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        return array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index > size) {
            System.out.println("Index out of bounds");
            return;
        } else if (index == size) {
            add(element);
            return;
        }
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(array[i] + ", ");
        }
        sb.append(array[size - 1] + "]");
        return sb.toString();
    }

}