import java.util.ArrayList;
import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private Node root;
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.val;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.val = successor.val;
                node.right = deleteMin(node.right);
            }
        }
        return node;
    }


    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        inorderTraversal(root, entries);
        return entries.iterator();
    }

    private void inorderTraversal(Node node, ArrayList<Entry<K, V>> entries) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left, entries);
        entries.add(new Entry<>(node.key, node.val));
        inorderTraversal(node.right, entries);
    }

    public static class Entry<K, V> {
        private K key;
        private V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    public void contains(V value, K key){    System.out.println(cHelper(root, value, key));
    }public boolean cHelper(Node node, V value, K key){
        if(node == null){        return false;
        }    int x = key.compareTo(node.key);
        if(value == root.val){        return true;
        } else if(x < 0) {        cHelper(node.left, value, key);
        } else if (x > 0){        cHelper(node.right, value, key);
        }    return false;
    }

}

