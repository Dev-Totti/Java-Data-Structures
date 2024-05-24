public class ArrayDynamic<T> {
    // Attributes
    private int size;
    private int capacity;
    private T[] array;

    // Constructors
    @SuppressWarnings("unchecked")
    public ArrayDynamic() {
        this.size = 0;
        this.capacity = 10;
        this.array = (T[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayDynamic(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
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

    @SuppressWarnings("unchecked")
    public void ensureCapacity() {
        if (isFull()) {
            capacity *= 2;
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    public void prepend(T element) {
        ensureCapacity();
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = element;
        size++;
    }

    public void append(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    public void add(T element) {
        append(element);
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