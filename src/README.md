# Assignment5
___
## put()

Description: Inserts a new key-value pair into the binary search tree. If the key already exists, the corresponding value is updated
```java
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
```
___
## get()

Description: Retrieves the value associated with the specified key from the binary search tree. It returns the value if the key exists, or null if the key is not found
```java
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
```
___
## delete()

Description: Removes the node with the specified key from the binary search tree, if it exists. If the key is found, the corresponding node is deleted, and the tree is restructured accordingly
```java
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
```
## iterator()
Description: Returns an iterator that allows iterating over the entries (key-value pairs) of the binary search tree in ascending order based on the keys. The iterator traverses the tree using an in-order traversal algorithm

```java
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
```
