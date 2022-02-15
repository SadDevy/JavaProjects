package Tests.TreeTests;

import Tests.IterativeTreeTests;
import Trees.BinarySearchTree;
import Trees.Node;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class IterativeTreeIntTests extends IterativeTreeTests<Integer> {
    @Test
    public void testEmptyTree() {
        BinarySearchTree<Integer> a = createTree(new ArrayList<>());
        Assert.assertNotNull(a);

        Assert.assertTrue(a.isEmpty());
    }

    @Test
    public void testRootOnly() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(0));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(0, a.getRoot().getData(), 0);

        Assert.assertFalse(a.getRoot().hasLeft());
        Assert.assertFalse(a.getRoot().hasRight());
    }

    @Test
    public void testRootRightChild() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(0, 1));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(0, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(1, a.getRoot().getRight().getData(), 0);

        Assert.assertFalse(a.getRoot().hasLeft());
    }

    @Test
    public void testRootBothChildren() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(2, 1, 3));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(2, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(3, a.getRoot().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().hasLeft());
        Assert.assertEquals(1, a.getRoot().getLeft().getData(), 0);
    }

    @Test
    public void testRootLeftChildLeftChild() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(2, 1, 0));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(2, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasLeft());
        Assert.assertEquals(1, a.getRoot().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().getLeft().hasLeft());
        Assert.assertEquals(0, a.getRoot().getLeft().getLeft().getData(), 0);
    }

    @Test
    public void testRootRightChildRightChild() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(0, 1, 2));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(0, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(1, a.getRoot().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().getRight().hasRight());
        Assert.assertEquals(2, a.getRoot().getRight().getRight().getData(), 0);
    }

    @Test
    public void testTwoChildrenRootAndLeft() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(3, 2, 4, 1));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(3, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasLeft());
        Assert.assertEquals(2, a.getRoot().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(4, a.getRoot().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().getLeft().hasLeft());
        Assert.assertEquals(1, a.getRoot().getLeft().getLeft().getData(), 0);
    }

    @Test
    public void testTwoChildrenRootAndRight() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(2, 1, 3, 4));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(2, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasLeft());
        Assert.assertEquals(1, a.getRoot().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(3, a.getRoot().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().getRight().hasRight());
        Assert.assertEquals(4, a.getRoot().getRight().getRight().getData(), 0);
    }

    @Test
    public void testThreeLevelsAllElements() {
        BinarySearchTree<Integer> a = createTree(Arrays.asList(4, 2, 1, 3, 6, 5, 7));
        Assert.assertNotNull(a);

        Assert.assertFalse(a.isEmpty());
        Assert.assertEquals(4, a.getRoot().getData(), 0);

        Assert.assertTrue(a.getRoot().hasLeft());
        Assert.assertEquals(2, a.getRoot().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().hasRight());
        Assert.assertEquals(6, a.getRoot().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().getLeft().hasLeft());
        Assert.assertEquals(1, a.getRoot().getLeft().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().getLeft().hasRight());
        Assert.assertEquals(3, a.getRoot().getLeft().getRight().getData(), 0);

        Assert.assertTrue(a.getRoot().getRight().hasLeft());
        Assert.assertEquals(5, a.getRoot().getRight().getLeft().getData(), 0);

        Assert.assertTrue(a.getRoot().getRight().hasRight());
        Assert.assertEquals(7, a.getRoot().getRight().getRight().getData(), 0);
    }

    @Test
    @UseDataProvider("getTestConstructorWithComparatorIntegerData")
    @Override
    public void testConstructorWithComparator(Comparator<Integer> comparator) {
        super.testConstructorWithComparator(comparator);
    }

    @DataProvider
    public static Object[][] getTestConstructorWithComparatorIntegerData() {
        return new Object[][] {
                {Comparator.naturalOrder()},
                {null}
        };
    }

    @Test
    @UseDataProvider("getTestConstructorWithCollectionData")
    @Override
    public void testConstructorWithCollection(List<Integer> values, List<Integer> expected) {
        super.testConstructorWithCollection(values, expected);
    }

    @DataProvider
    public static Object[][] getTestConstructorWithCollectionData() {
        return new Object[][] {
                {new ArrayList<>(), new ArrayList<>(0)},
                {Arrays.asList(0), Arrays.asList(0)},

                {Arrays.asList(0, 1), Arrays.asList(0, 1)},
                {Arrays.asList(1, 0), Arrays.asList(0, 1)},

                {Arrays.asList(1, 0, 2), Arrays.asList(0, 1, 2)},
                {Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 2)},
                {Arrays.asList(2, 1, 0), Arrays.asList(0, 1, 2)},

                {Arrays.asList(2, 1, 3, 0), Arrays.asList(0, 1, 2, 3)},
                {Arrays.asList(2, 1, 3, 2), Arrays.asList(1, 2, 2, 3)},
                {Arrays.asList(2, 1, 3, 3), Arrays.asList(1, 2, 3, 3)},
                {Arrays.asList(2, 1, 3, 4), Arrays.asList(1, 2, 3, 4)},

                {Arrays.asList(2, 1, 1, 2, 3), Arrays.asList(1, 1, 2, 2, 3)},
                {Arrays.asList(2, 1, 1, 1, 3), Arrays.asList(1, 1, 1, 2, 3)},
                {Arrays.asList(1, 2, 3, 3, 4), Arrays.asList(1, 2, 3, 3, 4)},
                {Arrays.asList(1, 3, 4, 4, 5), Arrays.asList(1, 3, 4, 4, 5)},
                {Arrays.asList(3, 1, 4, 4, 4), Arrays.asList(1, 3, 4, 4, 4)},
                {Arrays.asList(3, 1, 4, 5, 6), Arrays.asList(1, 3, 4, 5, 6)},

                {Arrays.asList(2, 1, 1, 2, 3, 3), Arrays.asList(1, 1, 2, 2, 3, 3)},
                {Arrays.asList(2, 1, 1, 2, 3, 1), Arrays.asList(1, 1, 1, 2, 2, 3)},
                {Arrays.asList(3, 1, 2, 3, 4, 2), Arrays.asList(1, 2, 2, 3, 3, 4)},
                {Arrays.asList(3, 1, 2, 3, 4, 3), Arrays.asList(1, 2, 3, 3, 3, 4)},
                {Arrays.asList(4, 2, 1, 3, 4, 5), Arrays.asList(1, 2, 3, 4, 4, 5)},
                {Arrays.asList(4, 2, 1, 3, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6)},

                {Arrays.asList(4, 2, 1, 1, 3, 5, 5), Arrays.asList(1, 1, 2, 3, 4, 5, 5)},
                {Arrays.asList(4, 2, 1, 2, 3, 5, 5), Arrays.asList(1, 2, 2, 3, 4, 5, 5)},
                {Arrays.asList(4, 2, 1, 3, 3, 5, 5), Arrays.asList(1, 2, 3, 3, 4, 5, 5)},
                {Arrays.asList(4, 2, 1, 3, 4, 5, 5), Arrays.asList(1, 2, 3, 4, 4, 5, 5)},
                {Arrays.asList(4, 2, 1, 3, 6, 5, 5), Arrays.asList(1, 2, 3, 4, 5, 5, 6)},
                {Arrays.asList(4, 2, 1, 3, 6, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6, 6)},
                {Arrays.asList(4, 2, 1, 3, 6, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 6, 7)}

        };
    }

    @Test
    @UseDataProvider("getTestAddData")
    @Override
    public void testAdd(List<Integer> values, Integer value, List<Integer> expected) {
        super.testAdd(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestAddData() {
        return new Object[][] {
                {new ArrayList<>(), 5, Arrays.asList(5)},

                {Arrays.asList(5), 7, Arrays.asList(5, 7)},
                {Arrays.asList(5, 7), 3, Arrays.asList(3, 5, 7)},

                {Arrays.asList(5, 7, 3), 2, Arrays.asList(2, 3, 5, 7)},
                {Arrays.asList(5, 7, 3, 2), 4, Arrays.asList(2, 3, 4, 5, 7)},
                {Arrays.asList(5, 7, 3, 2, 4), 6, Arrays.asList(2, 3, 4, 5, 6, 7)},
                {Arrays.asList(5, 7, 3, 2, 4, 6), 8, Arrays.asList(2, 3, 4, 5, 6, 7, 8)},
        };
    }

    @Test
    @UseDataProvider("getTestGetMaxData")
    @Override
    public void testGetMax(List<Integer> values, Integer expected) {
        super.testGetMax(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetMaxData() {
        return new Object[][] {
                {Arrays.asList(2), 2},

                {Arrays.asList(2, 3, 4), 4},
                {Arrays.asList(4, 2, 1), 4},

                {Arrays.asList(2, 1, 2, 3, 4), 4},
                {Arrays.asList(2, 1, 2, 3, 4, 5, 6), 6},
                {Arrays.asList(2, 1, 2, 3, 4, 5, 6, 7), 7}
        };
    }

    @Test
    @UseDataProvider("getTestGetMinData")
    @Override
    public void testGetMin(List<Integer> values, Integer expected) {
        super.testGetMin(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetMinData() {
        return new Object[][] {
                {Arrays.asList(2), 2},

                {Arrays.asList(4, 3, 2), 2},
                {Arrays.asList(2, 3, 4), 2},

                {Arrays.asList(2, 1, 2, 3, 0), 0},
                {Arrays.asList(2, 1, 2, 3, 1, 1, 2), 1},
                {Arrays.asList(2, 6, 2, 3, 4, 5, 6, 7), 2}
        };
    }

    @Test
    @UseDataProvider("getTestGetIteratorData")
    @Override
    public void testGetIterator(List<Integer> values, List<Integer> expected) {
        super.testGetIterator(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetIteratorData() {
        return new Object[][] {
                {Arrays.asList(5, 7, 3, 2, 1, 6), Arrays.asList(1, 2, 3, 5, 6, 7)},
                {Arrays.asList(7, 6, 5, 3, 2, 1), Arrays.asList(1, 2, 3, 5, 6, 7)},
                {Arrays.asList(1, 2, 3, 5, 6, 7), Arrays.asList(1, 2, 3, 5, 6, 7)},
        };
    }

    @Test
    @UseDataProvider("getTestGetReversedIteratorData")
    @Override
    public void testGetReversedIterator(List<Integer> values, List<Integer> expected) {
        super.testGetReversedIterator(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetReversedIteratorData() {
        return new Object[][] {
                {Arrays.asList(5, 7, 3, 2, 1, 6), Arrays.asList(7, 6, 5, 3, 2, 1)},
                {Arrays.asList(7, 6, 5, 3, 2, 1), Arrays.asList(7, 6, 5, 3, 2, 1)},
                {Arrays.asList(1, 2, 3, 5, 6, 7), Arrays.asList(7, 6, 5, 3, 2, 1)},
        };
    }

    @Test
    @UseDataProvider("getTestFindData")
    @Override
    public void testFind(List<Integer> values, Integer value, Node<Integer> expected) {
        super.testFind(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestFindData() {
        List<Integer> values = Arrays.asList(5, 7, 6, 2, 3, 1);
        return new Object[][] {
                {values, 5, new Node<>(5)},

                {values, 1, new Node<>(1)},
                {values, 6, new Node<>(6)},
                {values, 2, new Node<>(2)},

                {values, 0, null}
        };
    }

    @Test
    @UseDataProvider("getTestRemoveData")
    @Override
    public void testRemove(List<Integer> values, Integer value, boolean expected) {
        super.testRemove(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestRemoveData() {
        return new Object[][] {
                {new ArrayList<>(), 1, false},

                {Arrays.asList(1), 1, true},
                {Arrays.asList(1, 2), 2, true},
                {Arrays.asList(2, 1), 1, true},

                {Arrays.asList(1, 2, 3), 2, true},
                {Arrays.asList(2, 1, 0), 1, true},
                {Arrays.asList(1, 2, 0), 1, true}
        };
    }
}
