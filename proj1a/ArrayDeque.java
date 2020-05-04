
public class ArrayDeque<T> {

    private T[] items;
    private int capacity;
    private int size;
    private int startLoc; // position of first item in array
                          // last item locates at (startLoc + size -1 ) mod capacity

    public ArrayDeque() {
        capacity = 2;
        size = 0;
        startLoc = 0;
        items = (T[]) new Object[capacity];
    }

    public ArrayDeque(ArrayDeque other) {
        this.capacity = other.capacity;
        this.size = 0;
        this.startLoc = 0;
        this.items = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; ++i) {
            this.items[this.hash(i)] = (T) other.items[other.hash(i)];
        }
    }

    /**
     * from originLoc calculate the real location in array both indexes start from 0
     * 
     * @return the real location in array
     */
    private int hash(int originLoc) throws IndexOutOfBoundsException {
        if (originLoc >= 0 && originLoc < size) {
            return (originLoc + startLoc) % capacity;
        } else {
            throw new IndexOutOfBoundsException("Location out of boundary");
        }
    }

    private void changeCapacity(int newCapacity) {

        T[] newItems = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < size; ++i) {
            newItems[i] = items[hash(i)];
        }

        items = newItems;
        capacity = newCapacity;
        startLoc = 0;
    }

    public void addFirst(T item) {

        // if size > 0.6*capacity, increase
        if (3 * size >= 2 * capacity) {
            changeCapacity(2 * capacity);
        }

        ++size;
        --startLoc;
        if (startLoc < 0) {
            startLoc += capacity;
        }

        items[startLoc] = item;
    }

    public void addLast(T item) {

        // if size > 0.6*capacity, increase
        if (3 * size >= 2 * capacity) {
            changeCapacity(2 * capacity);
        }

        ++size;
        items[hash(size - 1)] = item;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(items[hash(i)]);
            System.out.print(" ");
        }
        System.out.print('\n');
    }

    public T removeFirst() {
        T res = items[hash(0)];
        items[hash(0)] = null;

        ++startLoc;
        if (startLoc >= capacity) {
            startLoc -= capacity;
        }
        --size;

        return res;
    }

    public T removeLast() {
        T res = items[hash(size - 1)];
        items[hash(size - 1)] = null;

        --size;

        return res;
    }

    public T get(int index) {
        return items[hash(index)];
    }
}