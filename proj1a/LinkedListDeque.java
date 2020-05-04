
public class LinkedListDeque<T> {

    private class Node {
        T item;
        Node pre;
        Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            pre = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        // Not use LinkedListDeque<T> as a parameter
        // because of the Autograder
        
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;

        int otherSize = other.size();

        for (int i = 0; i < otherSize; ++i) {
            this.addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        ++size;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
    }

    public void addLast(T item) {
        ++size;
        sentinel.pre = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        // Empty line
        System.out.print('\n');
    }

    public T removeFirst() {
        if (size() > 0) {
            --size;
            Node first = sentinel.next;
            Node second = sentinel.next.next;

            second.pre = sentinel;
            sentinel.next = second;

            return first.item;
        } else {
            return null;
        }

    }

    public T removeLast() {
        if (size() > 0) {
            --size;
            Node last = sentinel.pre;
            Node secondLast = sentinel.pre.pre;

            secondLast.next = sentinel;
            sentinel.pre = secondLast;

            return last.item;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (size() > index && index >= 0) {
            int cnt = 0;
            Node p = sentinel.next;

            while (cnt < index){
                p = p.next;
                ++cnt;
            }

            return p.item;
        } else {
            return null;
        }
    }

}