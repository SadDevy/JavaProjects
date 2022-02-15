package Tests.TreeTests;

import Tests.IterativeTreeTests;
import Trees.Node;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class IterativeTreeStringTests extends IterativeTreeTests<String> {
    @Test
    @UseDataProvider("getTestConstructorWithComparatorIntegerData")
    @Override
    public void testConstructorWithComparator(Comparator<String> comparator) {
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
    public void testConstructorWithCollection(List<String> values, List<String> expected) {
        super.testConstructorWithCollection(values, expected);
    }

    @DataProvider
    public static Object[][] getTestConstructorWithCollectionData() {
        return new Object[][] {
                {new ArrayList<>(), new ArrayList<>(0)},

                {Arrays.asList(""), Arrays.asList("")},

                {Arrays.asList("a", "b"), Arrays.asList("a", "b")},
                {Arrays.asList("b", "a"), Arrays.asList("a", "b")},

                {Arrays.asList("b", "a", "c"), Arrays.asList("a", "b", "c")},
                {Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c")},
                {Arrays.asList("c", "b", "a"), Arrays.asList("a", "b", "c")},

                {Arrays.asList("c", "b", "d", "a"), Arrays.asList("a", "b", "c", "d")},
                {Arrays.asList("c", "b", "d", "c"), Arrays.asList("b", "c", "c", "d")},
                {Arrays.asList("c", "b", "d", "d"), Arrays.asList("b", "c", "d", "d")},
                {Arrays.asList("c", "b", "d", "e"), Arrays.asList("b", "c", "d", "e")},

                {Arrays.asList("c", "b", "b", "c", "d"), Arrays.asList("b", "b", "c", "c", "d")},
                {Arrays.asList("c", "b", "b", "b", "d"), Arrays.asList("b", "b", "b", "c", "d")},
                {Arrays.asList("b", "c", "d", "d", "e"), Arrays.asList("b", "c", "d", "d", "e")},
                {Arrays.asList("b", "d", "e", "e", "f"), Arrays.asList("b", "d", "e", "e", "f")},
                {Arrays.asList("d", "b", "e", "e", "e"), Arrays.asList("b", "d", "e", "e", "e")},
                {Arrays.asList("d", "b", "e", "f", "g"), Arrays.asList("b", "d", "e", "f", "g")},

                {Arrays.asList("c", "b", "b", "c", "d", "d"), Arrays.asList("b", "b", "c", "c", "d", "d")},
                {Arrays.asList("c", "b", "b", "c", "d", "b"), Arrays.asList("b", "b", "b", "c", "c", "d")},
                {Arrays.asList("d", "b", "c", "d", "e", "c"), Arrays.asList("b", "c", "c", "d", "d", "e")},
                {Arrays.asList("d", "b", "c", "d", "e", "d"), Arrays.asList("b", "c", "d", "d", "d", "e")},
                {Arrays.asList("e", "c", "b", "d", "f", "e"), Arrays.asList("b", "c", "d", "e", "e", "f")},
                {Arrays.asList("e", "c", "b", "d", "f", "g"), Arrays.asList("b", "c", "d", "e", "f", "g")},

                {Arrays.asList("e", "c", "b", "b", "d", "f", "f"), Arrays.asList("b", "b", "c", "d", "e", "f", "f")},
                {Arrays.asList("e", "c", "b", "c", "d", "f", "f"), Arrays.asList("b", "c", "c", "d", "e", "f", "f")},
                {Arrays.asList("e", "c", "b", "d", "d", "f", "f"), Arrays.asList("b", "c", "d", "d", "e", "f", "f")},
                {Arrays.asList("e", "c", "b", "d", "e", "f", "f"), Arrays.asList("b", "c", "d", "e", "e", "f", "f")},
                {Arrays.asList("e", "c", "b", "d", "g", "f", "f"), Arrays.asList("b", "c", "d", "e", "f", "f", "g")},
                {Arrays.asList("e", "c", "b", "d", "g", "f", "g"), Arrays.asList("b", "c", "d", "e", "f", "g", "g")},
                {Arrays.asList("e", "c", "b", "d", "g", "f", "h"), Arrays.asList("b", "c", "d", "e", "f", "g", "h")}

        };
    }

    @Test
    @UseDataProvider("getTestAddData")
    @Override
    public void testAdd(List<String> values, String value, List<String> expected) {
        super.testAdd(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestAddData() {
        return new Object[][] {
                {new ArrayList<>(), "e", Arrays.asList("e")},

                {Arrays.asList("e"), "h", Arrays.asList("e", "h")},
                {Arrays.asList("e", "h"), "d", Arrays.asList("d", "e", "h")},

                {Arrays.asList("e", "h", "d"), "c", Arrays.asList("c", "d", "e", "h")},
                {Arrays.asList("e", "h", "d", "c"), "f", Arrays.asList("c", "d", "e", "f", "h")},
                {Arrays.asList("e", "h", "d", "c", "f"), "g", Arrays.asList("c", "d", "e", "f", "g", "h")},
                {Arrays.asList("e", "h", "d", "c", "f", "g"), "l", Arrays.asList("c", "d", "e", "f", "g", "h", "l")},
        };
    }

    @Test
    @UseDataProvider("getTestGetMaxData")
    @Override
    public void testGetMax(List<String> values, String expected) {
        super.testGetMax(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetMaxData() {
        return new Object[][] {
                {Arrays.asList("c"), "c"},

                {Arrays.asList("c", "d", "e"), "e"},
                {Arrays.asList("e", "c", "b"), "e"},

                {Arrays.asList("c", "b", "c", "d", "e"), "e"},
                {Arrays.asList("c", "b", "c", "d", "e", "f", "g"), "g"},
                {Arrays.asList("c", "b", "c", "d", "e", "f", "g", "h"), "h"}
        };
    }

    @Test
    @UseDataProvider("getTestGetMinData")
    @Override
    public void testGetMin(List<String> values, String expected) {
        super.testGetMin(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetMinData() {
        return new Object[][] {
                {Arrays.asList("c"), "c"},

                {Arrays.asList("e", "d", "c"), "c"},
                {Arrays.asList("c", "d", "e"), "c"},

                {Arrays.asList("c", "b", "c", "d", "a"), "a"},
                {Arrays.asList("c", "b", "c", "d", "b", "b", "c"), "b"},
                {Arrays.asList("c", "g", "c", "d", "e", "f", "g", "h"), "c"}
        };
    }

    @Test
    @UseDataProvider("getTestGetIteratorData")
    @Override
    public void testGetIterator(List<String> values, List<String> expected) {
        super.testGetIterator(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetIteratorData() {
        return new Object[][] {
                {Arrays.asList("f", "h", "d", "c", "b", "g"), Arrays.asList("b", "c", "d", "f", "g", "h")},
                {Arrays.asList("h", "g", "f", "d", "c", "b"), Arrays.asList("b", "c", "d", "f", "g", "h")},
                {Arrays.asList("b", "c", "d", "f", "g", "h"), Arrays.asList("b", "c", "d", "f", "g", "h")},
        };
    }

    @Test
    @UseDataProvider("getTestGetReversedIteratorData")
    @Override
    public void testGetReversedIterator(List<String> values, List<String> expected) {
        super.testGetReversedIterator(values, expected);
    }

    @DataProvider
    public static Object[][] getTestGetReversedIteratorData() {
        return new Object[][] {
                {Arrays.asList("f", "h", "d", "c", "b", "g"), Arrays.asList("h", "g", "f", "d", "c", "b")},
                {Arrays.asList("h", "g", "f", "d", "c", "b"), Arrays.asList("h", "g", "f", "d", "c", "b")},
                {Arrays.asList("b", "c", "d", "f", "g", "h"), Arrays.asList("h", "g", "f", "d", "c", "b")},
        };
    }

    @Test
    @UseDataProvider("getTestFindData")
    @Override
    public void testFind(List<String> values, String value, Node<String> expected) {
        super.testFind(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestFindData() {
        List<String> values = Arrays.asList("e", "h", "g", "c", "d", "b");
        return new Object[][] {
                {values, "e", new Node<>("e")},

                {values, "b", new Node<>("b")},
                {values, "g", new Node<>("g")},
                {values, "c", new Node<>("c")},

                {values, "a", null}
        };
    }

    @Test
    @UseDataProvider("getTestRemoveData")
    @Override
    public void testRemove(List<String> values, String value, boolean expected) {
        super.testRemove(values, value, expected);
    }

    @DataProvider
    public static Object[][] getTestRemoveData() {
        return new Object[][] {
                {new ArrayList<>(), "a", false},

                {Arrays.asList("a"), "a", true},
                {Arrays.asList("a", "b"), "b", true},
                {Arrays.asList("b", "a"), "a", true},

                {Arrays.asList("a", "b", "c"), "b", true},
                {Arrays.asList("b", "a", "l"), "a", true},
                {Arrays.asList("a", "b", "l"), "a", true}
        };
    }
}
