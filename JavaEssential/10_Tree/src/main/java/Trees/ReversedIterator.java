package Trees;

import java.util.Iterator;
import java.util.Stack;

public class ReversedIterator<TItem extends Comparable<TItem>>
        implements Iterator<TItem> {
    private BinarySearchTree<TItem> tree;
    private Stack<Node<TItem>> elements;
    private Node<TItem> node;

    private TItem currentItem;

    public ReversedIterator(BinarySearchTree<TItem> tree) {
        this.tree = tree;
        reset();
    }

    public void reset() {
        elements = new Stack<Node<TItem>>();
        node = tree.getRoot();

        currentItem = null;
    }

    @Override
    public boolean hasNext() {
        while (node != null || elements.size() > 0) {
            if (node == null) {
                node = elements.pop();
                currentItem = node.getData();

                node = node.getLeft();
                return true;
            } else {
                elements.push(node);
                node = node.getRight();
            }
        }

        return false;
    }

    @Override
    public TItem next() {
        return currentItem;
    }
}
