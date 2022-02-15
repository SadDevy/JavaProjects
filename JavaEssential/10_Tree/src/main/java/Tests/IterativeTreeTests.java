package Tests;

import Trees.BinarySearchTree;
import Trees.IterativeTree;

import java.util.Comparator;

public class IterativeTreeTests<TItem extends Comparable<TItem>>
        extends BinaryTreeTests<TItem> {
    @Override
    public BinarySearchTree<TItem> createTree(Iterable<TItem> collection) {
        return new IterativeTree<TItem>(collection);
    }

    @Override
    public BinarySearchTree<TItem> createTree(Comparator<TItem> comparator) {
        return new IterativeTree<TItem>(comparator);
    }

    @Override
    public BinarySearchTree<TItem> createTree(Iterable<TItem> collection, Comparator<TItem> comparator) {
        return new IterativeTree<TItem>(collection, comparator);
    }
}
