package deque;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        LinkedListDeque<Integer> queue = new LinkedListDeque<>();
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);

        System.out.println(queue.toString());
    }

    public LinkedListDeque() {
        sentinel = new Node(0, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new LinkedListIterator<T>();
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> curr;

        LinkedListIterator() {
            curr = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return curr != sentinel;
        }

        @Override
        public T next() {
            T item = curr.item;
            curr = curr.next;
            return item;
        }
    }

    public class Node<T> {
        private Node prev;
        private T item;
        private Node next;

        public Node() {
            prev = null;
            item = null;
            next = null;
        }

        public Node(T i, Node n, Node p) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    int size;

    @Override
    public void addFirst(T x) {
        size++;
        Node<T> n = new Node<T>(x, null, null);
        n.prev = sentinel;
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;
    }

    @Override
    public void addLast(T x) {
        size++;
        Node<T> n = new Node<T>(x, null, null);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
    }

    @Override
    public List<T> toList() {
        List<T> ls = new ArrayList<>();
        Node n = sentinel;
        int count = 0;
        while (count < size) {
            ls.add((T) n.next.item);
            n = n.next;
            count++;
        }
        return ls;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        return (T) remove(sentinel.next);
    }

    public T remove(Node<T> n) {
        size--;
        n.prev.next = n.next;
        n.next.prev = n.prev;
        return n.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        return (T) remove(sentinel.prev);
    }

    @Override
    public T get(int index) {
        Node s = sentinel;
        Node<T> item = new Node<>();
        if (index >= size || index < 0) {
            return null;
        }
        while (index != -1) {
            s = s.next;
            item = s;
            index--;
        }
        return item.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return (T) helperRecursive(sentinel.next, index);
    }

    public T helperRecursive(Node<T> n, int idx) {
        if (idx == 0) {
            return n.item;
        }
        return (T) helperRecursive(n.next, idx - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deque) {
            Deque<T> D = (Deque<T>) obj;
            if (this.size != D.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(D.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}



