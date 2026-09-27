import java.util.Objects;

public class DynamicArray<T> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public DynamicArray() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public void add(T element) {
        ensureCapacity();
        data[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = (T) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) data[index];
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
}