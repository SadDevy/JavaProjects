package TreesTests;

import Trees.IterativeTree;
import Trees.StudentTestInfo;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class IterativeTreeTests {
    @Test
    @UseDataProvider("getTestSerializationIntegersTestCases")
    public void testSerializationIntegers(Integer[] values, List<Integer> expected)
            throws IOException, ClassNotFoundException {
        IterativeTree<Integer> a = new IterativeTree<Integer>(Arrays.asList(values));
        Assert.assertNotNull(a);

        final String testSerializationIntegersFileName = "SerializedFiles/testSerializationIntegers.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testSerializationIntegersFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testSerializationIntegersFileName))) {
                IterativeTree<Integer> result = (IterativeTree<Integer>) in.readObject();

                List<Integer> actual = new ArrayList<>();
                for (Integer item : result)
                    actual.add(item);

                Assert.assertEquals(expected, actual);
            }
        }
    }

    @DataProvider
    public static Object[][] getTestSerializationIntegersTestCases() {
        return new Object[][] {
                {new Integer[0], new ArrayList<>()},
                {new Integer[] {5, 4, 6, 3, 5}, Arrays.asList(3, 4, 5, 5, 6)}
        };
    }

    @Test
    @UseDataProvider("getTestSerializationStudentsTestCases")
    public void testSerializationStudents(List<StudentTestInfo> values, List<StudentTestInfo> expected)
            throws IOException, ClassNotFoundException {
        IterativeTree<StudentTestInfo> a = new IterativeTree<StudentTestInfo>(values);
        Assert.assertNotNull(a);

        final String testSerializationStudentsFileName = "SerializedFiles/testSerializationStudents.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testSerializationStudentsFileName))) {
            out.writeObject(a);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testSerializationStudentsFileName))) {
                IterativeTree<StudentTestInfo> result = (IterativeTree<StudentTestInfo>) in.readObject();

                List<StudentTestInfo> actual = new ArrayList<>();
                for (StudentTestInfo item : result)
                    actual.add(item);

                Assert.assertEquals(expected, actual);
            }
        }
    }

    @DataProvider
    public static Object[][] getTestSerializationStudentsTestCases() {
        StudentTestInfo a = new StudentTestInfo("Павел", "Павлов", "Test", new Date(2020, 06, 14), 1);
        StudentTestInfo b = new StudentTestInfo("Павел", "Павлов", "Test", new Date(2020, 06, 14), 1);
        StudentTestInfo c = new StudentTestInfo("Павел", "Павлов", "Test", new Date(2020, 06, 14), 1);
        StudentTestInfo d = new StudentTestInfo("Павел", "Павлов", "Test", new Date(2020, 06, 14), 1);
        StudentTestInfo e = new StudentTestInfo("Павел", "Павлов", "Test", new Date(2020, 06, 14), 1);

        return new Object[][] {
                {new ArrayList<StudentTestInfo>(), new ArrayList<StudentTestInfo>()},
                {Arrays.asList(a, b, c, d, e), Arrays.asList(a, b, c, d, e)},
        };
    }
}
