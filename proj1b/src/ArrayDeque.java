
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(0);
        lld1.removeFirst();
        lld1.addLast(2);
        lld1.get(0);
        lld1.removeLast();
        lld1.addFirst(5);
        lld1.removeFirst();
        lld1.addFirst(7);
        lld1.removeFirst();
        lld1.addFirst(9);

        System.out.println(lld1.toList());

    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void extendSize(int capacity) {

        T[] newItems = (T[]) new Object[capacity * 2];

        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }

        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    private void shrinkHalfSize() {

        T[] newItems = (T[]) new Object[items.length / 2]; // new items

        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }

        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    private boolean isFull() {
        return size() == items.length;
    }

    @Override
    public void addFirst(T x) {

        if (isFull()) {
            extendSize(size());
        }

        items[nextFirst] = x;
        size++;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    @Override
    public void addLast(T x) {
        if (isFull()) {
            extendSize(items.length);
        }

        items[nextLast] = x;
        size++;
        nextLast = (nextLast + 1) % items.length;
    }


    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        if (isEmpty()) {
            return list;
        }

        for (int i = 0; i < size; i++) {
            list.add(get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T x = items[(nextFirst + 1) % items.length];
        size--;
        nextFirst = (nextFirst + 1) % items.length;

        if (size <  items.length / 4) {
            shrinkHalfSize();
        }

        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T x = items[(nextLast - 1 + items.length) % items.length];
        size--;
        nextLast = (nextLast - 1 + items.length) % items.length;

        if (size < items.length / 4) {
            shrinkHalfSize();
        }
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1 || items.length == 0) {
            return null;
        }
        int idx = (nextFirst + 1 + index + items.length) % items.length;
        return items[idx];
    }

}
