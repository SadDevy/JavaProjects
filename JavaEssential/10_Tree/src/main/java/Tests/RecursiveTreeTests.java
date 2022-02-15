package Tests;

import Trees.BinarySearchTree;
import Trees.RecursiveTree;

import java.util.Comparator;

public class RecursiveTreeTests<TItem extends Comparable<TItem>>
        extends BinaryTreeTests<TItem> {
    @Override
    public BinarySearchTree<TItem> createTree(Iterable<TItem> collection) {
        return new RecursiveTree<TItem>(collection);
    }

    @Override
    public BinarySearchTree<TItem> createTree(Comparator<TItem> comparator) {
        return new RecursiveTree<TItem>(comparator);
    }

    @Override
    public BinarySearchTree<TItem> createTree(Iterable<TItem> collection, Comparator<TItem> comparator) {
        return new RecursiveTree<TItem>(collection, comparator);
    }
}
