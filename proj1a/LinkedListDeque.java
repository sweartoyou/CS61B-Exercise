public class LinkedListDeque<T> {
    private class Node{
        private Node prev;
        private T item;
        private Node next;

        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node i = sentinel.next; i.next != sentinel; i = i.next) {
            System.out.print(i.item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return res;
    }

    public T get(int index) {
        if (size < index) return null;
        Node node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index -- ;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (size < index) return null;
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(LinkedListDeque<T>.Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
