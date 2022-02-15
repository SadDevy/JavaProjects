package Trees;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class IterativeTree<TItem extends Comparable<TItem>>
        extends BinarySearchTree<TItem> {
    public IterativeTree(Comparator<TItem> comparator) {
        super(comparator);
    }

    public IterativeTree(Iterable<TItem> collection) {
        super(collection);
    }

    public IterativeTree(Iterable<TItem> collection, Comparator<TItem> comparator) {
        super(collection, comparator);
    }

    @Override
    public void add(TItem data)
            throws NullPointerException {
        if (data == null)
            throw new NullPointerException("data");

        Node<TItem> node = new Node<TItem>(data);
        if (!isEmpty()) {
            Node<TItem> currentNode = getRoot();
            while (currentNode != null && currentNode != node) {
                if (compare(data, currentNode) <= 0) {
                    if (!currentNode.hasLeft())
                        currentNode.setLeft(node);
                    else
                        currentNode = currentNode.getLeft();
                }
                else {
                    if (!currentNode.hasRight())
                        currentNode.setRight(node);
                    else
                        currentNode = currentNode.getRight();
                }
            }
        }
        else {
            setRoot(node);
        }
    }

    @Override
    public Node<TItem> find(TItem data) {
        return find(data, new AtomicReference<>());
    }

    @Override
    protected Node<TItem> find(TItem data, AtomicReference<Node<TItem>> parent) {
        parent.set(null);

        Node<TItem> child = getRoot();
        while (child != null && compare(data, child) != 0) {
            parent.set(child);
            child = (compare(data, child) < 0) ? child.getLeft() : child.getRight();
        }

        return child;
    }

    private Iterable<TItem> getAroundInOrder() {
        Stack<Node<TItem>> elements = new Stack<Node<TItem>>();
        Node<TItem> root = getRoot();

        List<TItem> result = new ArrayList<>();
        while (elements.size() > 0 || root != null) {
            if (root == null) {
                root = elements.pop();
                result.add(root.getData());

                root = root.getRight();
            }
            else {
                elements.push(root);
                root = root.getLeft();
            }
        }

        return result;
    }

    @Override
    public Iterator<TItem> getIterator() {
        return getAroundInOrder().iterator();
    }

    @Override
    public Iterator<TItem> getReversedIterator() {
        return new ReversedIterator<TItem>(this);
    }

    @Override
    public Node<TItem> getRight(Node<TItem> root) {
        Node<TItem> result = null;
        if (root == null)
            return result;

        while (root.hasRight()) {
            root = root.getRight();
            result = root;
        }

        return result;
    }

    @Override
    public Node<TItem> getLeft(Node<TItem> root) {
        Node<TItem> result = null;
        if (root == null)
            return result;

        while (root.hasLeft()) {
            root = root.getLeft();
            result = root;
        }

        return result;
    }
}
