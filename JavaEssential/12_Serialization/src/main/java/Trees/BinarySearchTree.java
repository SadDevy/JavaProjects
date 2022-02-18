package Trees;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BinarySearchTree<TItem extends Comparable<TItem>>
        implements Iterable<TItem>, Serializable {
    private Node<TItem> root;

    public Node<TItem> getRoot() {
        return root;
    }

    protected void setRoot(Node<TItem> value) {
        root = value;
    }

    public boolean isEmpty() {
        return root == null;
    }

    protected Comparator<TItem> comparator;

    private boolean isForward() {
        if (isGeneralComparator()) {
            GeneralComparator<TItem> c = (GeneralComparator<TItem>) comparator;
            return c.isForward();
        }

        return true;
    }

    private boolean isGeneralComparator() {
        try {
            GeneralComparator<TItem> __ = (GeneralComparator<TItem>) comparator;
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    public BinarySearchTree(Comparator<TItem> comparator) {
        this.comparator = (comparator == null) ? Comparator.naturalOrder() : comparator;
    }

    public BinarySearchTree(Iterable<TItem> collection) {
        this(collection, null);
    }

    public BinarySearchTree(Iterable<TItem> collection, Comparator<TItem> comparator) {
        this(comparator);

        addRange(collection);
    }

    public abstract void add(TItem data);

    public void addRange(Iterable<TItem> data)
            throws NullPointerException {
        if (data == null)
            throw new NullPointerException("data");

        for (TItem item : data) {
            add(item);
        }
    }

    public abstract Node<TItem> find(TItem data);

    protected abstract Node<TItem> find(TItem data, AtomicReference<Node<TItem>> parent);

    public abstract Iterator<TItem> getIterator();

    public abstract Iterator<TItem> getReversedIterator();

    @Override
    public Iterator<TItem> iterator() {
        return isForward() ? getIterator() : getReversedIterator();
    }

    public abstract Node<TItem> getRight(Node<TItem> root);

    public abstract Node<TItem> getLeft(Node<TItem> root);

    public TItem getMax()
            throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Пустое дерево.");

        Node<TItem> max = isForward() ? getRight(root) : getLeft(root);
        return (max != null) ? max.getData() : root.getData();
    }

    public TItem getMin()
            throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Пустое дерево.");

        Node<TItem> min = isForward() ? getLeft(root) : getRight(root);
        return (min != null) ? min.getData() : root.getData();
    }

    protected int compare(TItem data, Node<TItem> node) {
        int result;
        if (node == null)
            result = 1;
        else
            result = comparator.compare(data, node.getData());

        result *= isForward() ? 1 : -1;
        return result;
    }

    public boolean remove(TItem data) {
        if (data == null)
            return false;

        AtomicReference<Node<TItem>> bReference = new AtomicReference<>();
        Node<TItem> a = find(data, bReference);

        if (a == null)
            return false;

        if (!a.hasLeft() && !a.hasRight()) {
            removeNodeHavingNoChildren(a, bReference.get());
            return true;
        }

        if (a.hasLeft() && a.hasRight()) {
            removeNodeHavingBothChildren(a, bReference.get());
            return true;
        }

        removeNodeHavingOneChild(a, bReference.get());
        return true;
    }

    private void removeNodeHavingNoChildren(Node<TItem> nodeToRemove, Node<TItem> parent) {
        if (nodeToRemove == root) {
            root = null;
            return;
        }

        if (parent.getLeft() == nodeToRemove) {
            parent.setLeft(null);
            return;
        }

        parent.setRight(null);
    }

    private void removeNodeHavingOneChild(Node<TItem> nodeToRemove, Node<TItem> parent) {
        Node<TItem> node = (nodeToRemove.hasLeft()) ? nodeToRemove.getLeft() : nodeToRemove.getRight();
        if (nodeToRemove == root) {
            root = node;
            return;
        }

        if (parent.getLeft() == nodeToRemove) {
            parent.setLeft(node);
            return;
        }

        parent.setRight(node);
    }

    private void removeNodeHavingBothChildren(Node<TItem> nodeToRemove, Node<TItem> parent) {
        if (nodeToRemove != root) {
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(nodeToRemove.getRight());
            }

            if (parent.getRight() == nodeToRemove) {
                parent.setRight(nodeToRemove.getRight());
            }
        }
        else {
            root = root.getRight();
        }

        AtomicReference<Node<TItem>> aReference = new AtomicReference<>();
        find(nodeToRemove.getLeft().getData(), aReference);

        aReference.get().setLeft(nodeToRemove.getLeft());
    }
}
