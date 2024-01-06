import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size = 0;
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);
        size++;
    }
    public Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        }else {
            x.value = value;
            size--;
        }
        return x;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        Node x = get(root, key);
        if (x == null) {
            return null;
        }else {
            return x.value;
        }
    }
    private Node get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        }else {
            return x;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(root, key) != null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

