public class HeapMin<T extends Comparable<T>> {
    // Attributes
    private ArrayDynamic<T> heap;

    // Constructors
    public HeapMin() {
        this.heap = new ArrayDynamic<>();
    }

    // Methods
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public T get(int index) {
        return heap.get(index);
    }

    public int findIndex(T data) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public void add(T data) {
        heap.add(data);
        heapifyUp(size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T data = get(0);
        heap.set(0, get(size() - 1));
        heap.remove(size() - 1);
        heapifyDown(0);
        return data;
    }

    public void remove(T data) {
        int index = findIndex(data);
        if (index == -1) {
            return;
        }

        heap.set(index, get(size() - 1));
        heap.remove(size() - 1);

        if (get(index).compareTo(getParent(index)) < 0) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
    }

    // Heapify methods
    public void heapifyUp(int index) {
        while (hasParent(index) && getParent(index).compareTo(get(index)) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (get(index).compareTo(get(smallerChildIndex)) < 0) {
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
        return get(getParentIndex(index));
    }

    public T getLeftChild(int index) {
        return get(getLeftChildIndex(index));
    }

    public T getRightChild(int index) {
        return get(getRightChildIndex(index));
    }

    public void swap(int index1, int index2) {
        T temp = get(index1);
        heap.set(index1, get(index2));
        heap.set(index2, temp);
    }

    @Override
    public String toString() {
        return heap.toString();
    }

}