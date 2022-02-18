package TreesTests;

import Trees.KeyValuePair;
import Trees.KeyValuePairTree;
import Trees.Node;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class KeyValuePairTreeTests {
    @Test
    @UseDataProvider("getTestNodeSerialization")
    public void testNodeSerialization(
            KeyValuePair<String, Integer> data,
            Node<KeyValuePair<String, Integer>> right,
            Node<KeyValuePair<String, Integer>> left
    ) throws IOException, ClassNotFoundException {
        Node<KeyValuePair<String, Integer>> a = new Node<>(data, right, left);
        Assert.assertNotNull(a);

        final String testNodeSerializationFileName = "SerializedFiles/testNodeSerialization.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testNodeSerializationFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testNodeSerializationFileName))) {
                Node<KeyValuePair<String, Integer>> result = (Node<KeyValuePair<String, Integer>>) in.readObject();

                Assert.assertEquals(data, a.getData());
                Assert.assertEquals(left, a.getLeft());
                Assert.assertEquals(right, a.getRight());
            }
        }
    }

    @DataProvider
    public static Object[][] getTestNodeSerialization() {
        return new Object[][] {
                {new KeyValuePair<String, Integer>("Танк", 100), null, null},
                {new KeyValuePair<String, Integer>("Танк", 100), new Node<KeyValuePair<String, Integer>>(
                        new KeyValuePair<String, Integer>("Самолет", 140)
                ), null},
                {new KeyValuePair<String, Integer>("Танк", 100), new Node<KeyValuePair<String, Integer>>(
                        new KeyValuePair<String, Integer>("Самолет", 140)
                ), new Node<KeyValuePair<String, Integer>>(
                        new KeyValuePair<String, Integer>("Авианосец", 600)
                )},
        };
    }

    @Test
    @UseDataProvider("getTestTreeSerialization")
    public void testTreeSerialization(
            List<KeyValuePair<String, Integer>> values,
            List<KeyValuePair<String, Integer>> expected
    ) throws IOException, ClassNotFoundException {
        KeyValuePairTree<String, Integer> a = new KeyValuePairTree<String, Integer>(values);
        Assert.assertNotNull(a);

        final String testTreeSerializationFileName = "SerializedFiles/testTreeSerialization.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testTreeSerializationFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testTreeSerializationFileName))) {
                KeyValuePairTree<String, Integer> result = (KeyValuePairTree<String, Integer>) in.readObject();

                List<KeyValuePair<String, Integer>> actual = new ArrayList<>();
                for (KeyValuePair<String, Integer> item : result)
                    actual.add(item);

                Assert.assertEquals(expected, actual);
            }

        }
    }

    @DataProvider
    public static Object[][] getTestTreeSerialization() {
        return new Object[][] {
                {new ArrayList<KeyValuePair<String, Integer>>(), new ArrayList<KeyValuePair<String, Integer>>()},
                {Arrays.asList(
                        new KeyValuePair<String, Integer>("Танк", 100),
                        new KeyValuePair<String, Integer>("Самолет", 140),
                        new KeyValuePair<String, Integer>("Авианосец", 600),
                        new KeyValuePair<String, Integer>("Гаубица", 80)
                ), Arrays.asList(
                        new KeyValuePair<String, Integer>("Авианосец", 600),
                        new KeyValuePair<String, Integer>("Гаубица", 80),
                        new KeyValuePair<String, Integer>("Самолет", 140),
                        new KeyValuePair<String, Integer>("Танк", 100)
                )}
        };
    }
}
