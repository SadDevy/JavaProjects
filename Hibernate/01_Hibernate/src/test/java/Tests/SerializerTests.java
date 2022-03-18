package Tests;

import Entities.StudentTest;
import Utilities.Serializer;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.io.IOException;

public class SerializerTests {
    private String filePath;

    @Before
    public void setUp() {
        filePath = "test.out";
    }

    @Test
    public void testSerialize_testAndFilePathNotNull_Success()
            throws IOException, ClassNotFoundException {
        final int id = 1;
        final String name = "Test";
        final String theory = "Theory";
        final String theoryUrl = "TheoryUrl";
        final boolean theoryIsShown = true;
        final int questionsCount = 10;
        final int rightCount = 10;

        final StudentTest expected = new StudentTest(id, name, theory, theoryUrl, theoryIsShown, questionsCount, rightCount);
        Serializer.serialize(expected, filePath);

        StudentTest actual = Serializer.deserialize(filePath);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSerialize_testIdNull_Exception()
            throws IOException, IllegalArgumentException {
        Serializer.serialize(null, filePath);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSerialize_filePathIsNull_Exception()
            throws IOException, IllegalArgumentException {
        Serializer.serialize(new StudentTest(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSerialize_filePathIsEmpty_Exception()
            throws IOException, IllegalArgumentException {
        Serializer.serialize(new StudentTest(), "");
    }

    @Test
    public void testDeserialize_filePathExists_Success()
            throws IOException, ClassNotFoundException {
        StudentTest test = Serializer.deserialize(filePath);

        Assert.assertNotNull(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialize_filePathNotExists_Exception()
            throws IOException, ClassNotFoundException, IllegalArgumentException {
        Serializer.deserialize("some path");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialize_filePathIsNull_Exception()
            throws IOException, ClassNotFoundException, IllegalArgumentException {
        Serializer.deserialize(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialize_filePathIsEmpty_Exception()
            throws IOException, ClassNotFoundException, IllegalArgumentException {
        Serializer.deserialize("");
    }
}
