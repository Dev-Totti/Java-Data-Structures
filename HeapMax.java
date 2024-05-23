public class HeapMax<T extends Comparable<T>> {
    // Attributes
    private ArrayDynamic<T> heap;

    // Constructors
    public HeapMax() {
        this.heap = new ArrayDynamic<>();
    }

    // Methods
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public int size() {
        return this.heap.size();
    }

    public T get(int index) {
        return this.heap.get(index);
    }

    public int findIndex(T data) {
        for (int i = 0; i < size(); i++) {
            if (this.get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public void add(T data) {
        this.heap.add(data);
        this.heapifyUp(size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return this.get(0);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T data = this.get(0);
        this.heap.set(0, get(size() - 1));
        this.heap.remove(size() - 1);
        this.heapifyDown(0);
        return data;
    }

    public void remove(T data) {
        int index = findIndex(data);
        if (index == -1) {
            return;
        }

        this.heap.set(index, get(size() - 1));
        this.heap.remove(size() - 1);

        if (this.get(index).compareTo(getParent(index)) > 0) {
            this.heapifyUp(index);
        } else {
            this.heapifyDown(index);
        }
    }

    // Heapify methods
    public void heapifyUp(int index) {
        while (hasParent(index) && getParent(index).compareTo(this.get(index)) < 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) > 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (this.get(index).compareTo(this.get(smallerChildIndex)) > 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    // Helper methods
    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    public boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size();
    }

    public boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size();
    }

    public T getParent(int index) {
        return this.get(getParentIndex(index));
    }

    public T getLeftChild(int index) {
        return this.get(getLeftChildIndex(index));
    }

    public T getRightChild(int index) {
        return this.get(getRightChildIndex(index));
    }

    public void swap(int index1, int index2) {
        T temp = this.get(index1);
        this.heap.set(index1, this.get(index2));
        this.heap.set(index2, temp);
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }

}