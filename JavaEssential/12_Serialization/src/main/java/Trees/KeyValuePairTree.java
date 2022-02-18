package Trees;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class KeyValuePairTree<TKey extends Comparable<TKey>, TValue>
        extends IterativeTree<KeyValuePair<TKey, TValue>>
        implements Serializable {
    public List<TKey> getKeys() {
        List<TKey> collection = new ArrayList<>();
        for (KeyValuePair<TKey, TValue> pairs : getAroundPreorder())
            collection.add(pairs.getKey());

        return collection;
    }

    public KeyValuePairTree(Comparator<KeyValuePair<TKey, TValue>> comparator) {
        super(comparator);
    }

    public KeyValuePairTree(Collection<KeyValuePair<TKey, TValue>> collection) {
        super(collection);
    }

    public KeyValuePairTree(Collection<KeyValuePair<TKey, TValue>> collection, Comparator<KeyValuePair<TKey, TValue>> comparator) {
        super(collection, comparator);
    }

    @Override
    public void add(KeyValuePair<TKey, TValue> data)
            throws NullPointerException {
        if (contains(data.getKey()))
            throw new IllegalStateException("Элемент с таким ключом уже существует.");

        super.add(data);
    }

    public boolean contains(TKey key) {
        if (key == null || isEmpty())
            return false;

        return findKeyValuePair(key) != null;
    }

    private KeyValuePair<TKey, TValue> findKeyValuePair(TKey key) {
        if (key == null)
            return null;

        Node<KeyValuePair<TKey, TValue>> result = find(new KeyValuePair<>(key));
        if (result != null)
            return result.getData();

        return null;
    }

    public boolean tryGetValue(TKey key, AtomicReference<TValue> valueReference) {
        valueReference.set(null);

        if (key == null)
            return false;

        KeyValuePair<TKey, TValue> result = findKeyValuePair(key);
        if (result != null)
            valueReference.set(result.getValue());

        return true;
    }

    public boolean removeByKey(TKey key) {
        AtomicReference<TValue> valueReference = new AtomicReference<>();
        if (!tryGetValue(key, valueReference))
            return false;

        return remove(new KeyValuePair<>(key, valueReference.get()));
    }

    public void copyTo(KeyValuePairTree<TKey, TValue> tree) {
        tree.addRange(getAroundPreorder());
    }

    private Iterable<KeyValuePair<TKey, TValue>> getAroundPreorder() {
        Stack<Node<KeyValuePair<TKey, TValue>>> elements = new Stack<>();
        Node<KeyValuePair<TKey, TValue>> root = getRoot();
        elements.push(root);

        ArrayList<KeyValuePair<TKey, TValue>> result = new ArrayList<>();
        while (elements.size() > 0) {
            root = elements.pop();

            result.add(root.getData());

            if (root.hasRight())
                elements.push(root.getRight());

            if (root.hasLeft())
                elements.push(root.getLeft());
        }

        return result;
    }
}
