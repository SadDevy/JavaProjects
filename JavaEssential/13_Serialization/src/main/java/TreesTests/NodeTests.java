package TreesTests;

import Trees.Node;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;

@RunWith(DataProviderRunner.class)
public class NodeTests {
    @Test
    @UseDataProvider("getTestSerializationTestCases")
    public void testSerialization(
            Integer data,
            Node<Integer> left,
            Node<Integer> right
    ) throws IOException, ClassNotFoundException {
        Node<Integer> a = new Node<>(data, right, left);
        Assert.assertNotNull(a);

        final String testSerializationFileName = "SerializedFiles/testSerialization.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testSerializationFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testSerializationFileName))) {
                Node<Integer> actual = (Node<Integer>) in.readObject();

                Assert.assertEquals(data, actual.getData());
                Assert.assertEquals(left, actual.getLeft());
                Assert.assertEquals(right, actual.getRight());
            }
        }
    }

    @DataProvider
    public static Object[][] getTestSerializationTestCases() {
        return new Object[][] {
                {1, null, null},
                {1, new Node<Integer>(0), null},
                {1, new Node<Integer>(0), new Node<Integer>(2)}
        };
    }
}
