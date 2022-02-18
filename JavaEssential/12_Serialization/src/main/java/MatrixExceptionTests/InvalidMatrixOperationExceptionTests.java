package MatrixExceptionTests;

import MatrixException.InvalidMatrixOperationException;
import MatrixException.MatrixSize;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class InvalidMatrixOperationExceptionTests {
    @Test
    public void testSerialization()
            throws IOException, ClassNotFoundException {
        final int expectedRowCount = 2;
        final int expectedColumnCount = 2;
        MatrixSize size = new MatrixSize(expectedRowCount, expectedColumnCount);

        InvalidMatrixOperationException exception = new InvalidMatrixOperationException(size, size);

        final String testFileName = "SerializedFiles/testMatrixExceptionSerialization.out";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testFileName))) {
            out.writeObject(exception);

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(testFileName))) {
                InvalidMatrixOperationException actual = (InvalidMatrixOperationException) in.readObject();

                Assert.assertEquals(expectedRowCount, actual.getARowCount());
                Assert.assertEquals(expectedColumnCount, actual.getAColumnCount());
                Assert.assertEquals(expectedRowCount, actual.getBRowCount());
                Assert.assertEquals(expectedColumnCount, actual.getBColumnCount());
            }
        }
    }
}
