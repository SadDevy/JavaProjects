package Tests;

import Trees.BinarySearchTree;
import Trees.Node;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

public abstract class BinaryTreeTests<TItem extends Comparable<TItem>> {
    public abstract BinarySearchTree<TItem> createTree(Iterable<TItem> collection);
    public abstract BinarySearchTree<TItem> createTree(Comparator<TItem> comparator);
    public abstract BinarySearchTree<TItem> createTree(Iterable<TItem> collection, Comparator<TItem> comparator);

    @Test
    public void testConstructorWithComparator(Comparator<TItem> comparator) {
        BinarySearchTree<TItem> a = createTree(comparator);
        Assert.assertNotNull(a);
    }

    public void testConstructorWithCollection(List<TItem> values, List<TItem> expected) {
        BinarySearchTree<TItem> a = createTree(values);
        Assert.assertNotNull(a);

        List<TItem> actual = new ArrayList<>();
        a.forEach(n -> actual.add(n));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testConstructorWithCollectionAndComparator() {
        BinarySearchTree<TItem> a = createTree(new ArrayList<>(), Comparator.naturalOrder());
        Assert.assertNotNull(a);
    }

    public void testAdd(List<TItem> values, TItem value, List<TItem> expected) {
        BinarySearchTree<TItem> a = createTree(values);
        a.add(value);

        List<TItem> actual = new ArrayList<>();
        a.forEach(n -> actual.add(n));

        Assert.assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testAddRangeException() {
        exceptionRule.expect(NullPointerException.class);

        BinarySearchTree<TItem> a = createTree(new ArrayList<>());
        a.addRange(null);
    }

    public void testGetMax(List<TItem> values, TItem expected) {
        BinarySearchTree<TItem> a = createTree(values);
        Assert.assertNotNull(a);

        Assert.assertEquals(expected, a.getMax());
    }

    @Test
    public void testGetMaxException() {
        exceptionRule.expect(IllegalStateException.class);

        BinarySearchTree<TItem> a = createTree(new ArrayList<>());
        a.getMax();
    }

    public void testGetMin(List<TItem> values, TItem expected) {
        BinarySearchTree<TItem> a = createTree(values);
        Assert.assertNotNull(a);

        Assert.assertEquals(expected, a.getMin());
    }

    @Test
    public void testGetMinException() {
        exceptionRule.expect(IllegalStateException.class);

        BinarySearchTree<TItem> a = createTree(new ArrayList<>());
        a.getMin();
    }

    public void testGetIterator(List<TItem> values, List<TItem> expected) {
        BinarySearchTree<TItem> a = createTree(values);

        List<TItem> actual = new ArrayList<>();
        a.forEach(n -> actual.add(n));

        Assert.assertEquals(expected, actual);
    }

    public void testGetReversedIterator(List<TItem> values, List<TItem> expected) {
        BinarySearchTree<TItem> a = createTree(values);

        Iterator<TItem> iterator = a.getReversedIterator();
        List<TItem> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        Assert.assertEquals(expected, actual);
    }

    public void testFind(List<TItem> values, TItem value, Node<TItem> expected) {
        BinarySearchTree<TItem> a = createTree(values);
        Assert.assertEquals(expected, a.find(value));
    }

    public void testRemove(List<TItem> values, TItem value, boolean expected) {
        BinarySearchTree<TItem> a = createTree(values);
        boolean actual = a.remove(value);

        for (TItem item : a) {
            Assert.assertNotEquals(item, value);
        }

        Assert.assertEquals(expected, actual);
    }
}
