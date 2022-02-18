package TreesTests;

import Trees.KeyValuePair;
import Trees.KeyValuePairTree;
import Trees.WordsDictionary;
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
public class WordsDictionaryTests {
    @Test
    @UseDataProvider("testSerializationDictionaryTestCases")
    public void testSerializationDictionary(
            List<KeyValuePair<String, KeyValuePairTree<String, String[]>>> values,
            List<KeyValuePair<String, KeyValuePairTree<String, String[]>>> expected
    ) throws IOException, ClassNotFoundException {
        WordsDictionary a = new WordsDictionary(values);

        final String testSerializationDictionaryFileName = "SerializedFiles/testSerializationDictionary.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testSerializationDictionaryFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testSerializationDictionaryFileName))) {
                WordsDictionary result = (WordsDictionary) in.readObject();

                List<KeyValuePair<String, KeyValuePairTree<String, String[]>>> actual = new ArrayList<>();
                result.forEach(n -> actual.add(n));

                Assert.assertEquals(expected, actual);
            }
        }
    }

    @DataProvider
    public static Object[][] testSerializationDictionaryTestCases() {
        return new Object[][]{
                {new ArrayList<KeyValuePair<String, KeyValuePairTree<String, String[]>>>(), new ArrayList<KeyValuePair<String, KeyValuePairTree<String, String[]>>>()},
                {
                    Arrays.<KeyValuePair<String, KeyValuePairTree<String, String[]>>>asList(
                        new KeyValuePair<String, KeyValuePairTree<String, String[]>>("Hello", new KeyValuePairTree<String, String[]>(
                                Arrays.asList(
                                        new KeyValuePair<String, String[]>("fr", new String[]{"bonjour"}),
                                        new KeyValuePair<String, String[]>("ru", new String[]{"Привет", "Здравствуй"})
                                )
                        ))),
                        Arrays.<KeyValuePair<String, KeyValuePairTree<String, String[]>>>asList(
                                new KeyValuePair<>("Hello", new KeyValuePairTree<String, String[]>(
                                        Arrays.asList(
                                                new KeyValuePair<String, String[]>("fr", new String[]{"bonjour"}),
                                                new KeyValuePair<String, String[]>("ru", new String[]{"Привет", "Здравствуй"})
                                        )
                                )))
                }
        };
    }
}
