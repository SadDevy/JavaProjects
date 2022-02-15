package Trees;

import org.objectweb.asm.commons.GeneratorAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RecursiveTree<TItem extends Comparable<TItem>>
        extends BinarySearchTree<TItem> {
    public RecursiveTree(Comparator<TItem> comparator) {
        super(comparator);
    }

    public RecursiveTree(Iterable<TItem> collection) {
        super(collection);
    }

    public RecursiveTree(Iterable<TItem> collection, Comparator<TItem> comparator) {
        super(collection, comparator);
    }

    @Override
    public void add(TItem data)
            throws NullPointerException {
        if (data == null)
            throw new NullPointerException("data");

        if (isEmpty()) {
            setRoot(new Node<TItem>(data, null, null));
            return;
        }

        add(data, getRoot());
    }

    private void add(TItem data, Node<TItem> current) {
        if (data.compareTo(current.getData()) <= 0) {
            if (!current.hasLeft())
                current.setLeft(new Node<TItem>(data));
            else
                add(data, current.getLeft());
        }
        else {
            if (!current.hasRight())
                current.setRight(new Node<TItem>(data));
            else
                add(data, current.getRight());
        }
    }

    @Override
    public Node<TItem> find(TItem data) {
        return find(data, new AtomicReference<Node<TItem>>());
    }

    @Override
    protected Node<TItem> find(TItem data, AtomicReference<Node<TItem>> parent) {
        parent.set(null);
        return find(data, getRoot(), parent);
    }

    private Node<TItem> find(TItem data, Node<TItem> current, AtomicReference<Node<TItem>> parent) {
        if (current == null || data.compareTo(current.getData()) == 0)
            return current;

        parent.set(current);
        if (data.compareTo(current.getData()) <= 0) {
            if (!current.hasLeft())
                return current.getLeft();

            return find(data, current.getLeft(), parent);
        }
        else {
            if (!current.hasRight())
                return current.getRight();

            return find(data, current.getRight(), parent);
        }
    }

    @Override
    public Iterator<TItem> getIterator() {
        return getInOrder(getRoot()).iterator();
    }

    private Iterable<TItem> getInOrder(Node<TItem> root) { //!!!
        return new Iterable<TItem>() {
            @Override
            public Iterator<TItem> iterator() {
                return new Iterator<TItem>() {
                    private TItem current;

                    @Override
                    public boolean hasNext() {
                        if (root == null)
                            return false;

                        if (root.hasLeft()) {
                            for (TItem item : getInOrder(root.getLeft())) {
                                current = item;
                                return true;
                            }
                        }

                        current = root.getData();

                        if (root.hasRight()) {
                            for (TItem item : getInOrder(root.getRight())) {
                                current = item;
                                return true;
                            }
                        }

                        return false;
                    }

                    @Override
                    public TItem next() {
                        return current;
                    }
                };
            }
        };
    }

    @Override
    public Iterator<TItem> getReversedIterator() {
        return new ReversedIterator<>(this);
    }

    @Override
    public Node<TItem> getRight(Node<TItem> root) {
        if (!root.hasRight())
            return root;

        return getRight(root.getRight());
    }

    @Override
    public Node<TItem> getLeft(Node<TItem> root) {
        if (!root.hasLeft())
            return root;

        return getLeft(root.getLeft());
    }
}
