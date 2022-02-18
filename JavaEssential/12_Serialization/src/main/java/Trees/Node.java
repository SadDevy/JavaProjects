package Trees;

import java.io.Serializable;
import java.util.Objects;

public class Node<TItem extends Comparable<TItem>>
        implements Serializable {
    private TItem data;

    public TItem getData() {
        return data;
    }

    private Node<TItem> right;

    public Node<TItem> getRight() {
        return right;
    }

    public void setRight(Node<TItem> value) {
        right = value;
    }

    private Node<TItem> left;

    public Node<TItem> getLeft() {
        return left;
    }

    public void setLeft(Node<TItem> value) {
        left = value;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public Node(TItem data) {
        this(data, null);
    }

    public Node(TItem data, Node<TItem> right) {
        this(data, right, null);
    }

    public Node(TItem data, Node<TItem> right, Node<TItem> left) {
        this.data = data;
        this.right = right;
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isNode(o))
            return false;

        Node<TItem> node = (Node<TItem>) o;
        return data.compareTo(node.getData()) == 0;
    }

    private boolean isNode(Object o) {
        try {
            Node<TItem> __ = (Node<TItem>) o;
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, right, left);
    }
}

