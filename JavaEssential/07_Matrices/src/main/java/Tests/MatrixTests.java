package Tests;

import Matrices.InvalidMatrixOperationException;
import Matrices.Matrix;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class MatrixTests {
    @Test
    @UseDataProvider("getTestColCountData")
    public void testColCount(double[][] values, int expected) {
        Matrix a = new Matrix(values);

        Assert.assertEquals(expected, a.getColumnCount(), 0);
    }

    @DataProvider
    public static Object[][] getTestColCountData() {
        return new Object[][]{
                {new double[1][1], 1},
                {new double[][]{{1}, {1}}, 1},

                {new double[][]{{1, 0}, {1, 0}}, 2},
                {new double[][]{{0, 1}, {0, 1}}, 2},

                {new double[][]{{0, 0, 0}, {0, 0, 0}}, 3},
                {new double[][]{{0, 1, 0}, {0, 1, 0}}, 3},
                {new double[][]{{1, 0, 1}, {1, 0, 1}}, 3},

                {new double[][]{{0, 1, 0, 0}, {0, 1, 0, 0}}, 4},
                {new double[][]{{0, 1, 1, 1}, {0, 1, 1, 0}}, 4},
                {new double[][]{{0, 1, 1, 1}, {0, 1, 1, 1}}, 4},

                {new double[][]{{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}}, 5}
        };
    }

    @Test
    @UseDataProvider("getTestRowCountData")
    public void testRowCount(double[][] values, int expected) {
        Matrix a = new Matrix(values);

        Assert.assertEquals(expected, a.getRowCount());
    }

    @DataProvider
    public static Object[][] getTestRowCountData() {
        return new Object[][]{
                {new double[1][1], 1},
                {new double[][]{{1}, {1}}, 1},

                {new double[][]{{1, 0}, {1, 0}}, 2},
                {new double[][]{{0, 1}, {0, 1}}, 2},

                {new double[][]{{0, 0, 0}, {0, 0, 0}}, 3},
                {new double[][]{{0, 1, 0}, {0, 1, 0}}, 3},
                {new double[][]{{1, 0, 1}, {1, 0, 1}}, 3},

                {new double[][]{{0, 1, 0, 0}, {0, 1, 0, 0}}, 4},
                {new double[][]{{0, 1, 1, 1}, {0, 1, 1, 0}}, 4},
                {new double[][]{{0, 1, 1, 1}, {0, 1, 1, 1}}, 4},

                {new double[][]{{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}}, 5}
        };
    }


    @Test
    @UseDataProvider("testDefaultConstructorData")
    public void testDefaultConstructor(int rowCount, int colCount) {
        Matrix a = new Matrix(rowCount, colCount);

        Assert.assertNotNull(a);
    }

    @DataProvider
    public static Object[][] testDefaultConstructorData() {
        return new Object[][]{
                {1, 0},
                {0, 1},
                {1, 1},

                {2, 1},
                {1, 2},
                {2, 2},
        };
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDefaultConstructorException() {
        exceptionRule.expect(IllegalStateException.class);

        Matrix __ = new Matrix(0, 0);
    }

    @Test
    @UseDataProvider("testConstructorData")
    public void testConstructor(double[][] values, double[][] expected) {
        Matrix a = new Matrix(values);

        Assert.assertNotNull(a);

        double[][] result = new double[a.getRowCount()][a.getColumnCount()];
        for (int i = 0; i < a.getRowCount(); i++) {
            for (int j = 0; j < a.getColumnCount(); j++) {
                result[i][j] = a.getElement(i, j);
            }
        }

        Assert.assertEquals(expected, result);
    }

    @DataProvider
    public static Object[][] testConstructorData() {
        return new Object[][]{
                {new double[1][1], new double[1][1]},

                {new double[][]{{-1}, {-1}}, new double[][]{{-1}, {-1}}},

                {new double[][]{{-1, -1}, {-1, -1}}, new double[][]{{-1, -1}, {-1, -1}}},

                {new double[][]{{0, -2, 0}, {0, -2, 0}}, new double[][]{{0, -2, 0}, {0, -2, 0}}},

                {new double[][]{{0, -2, 0, 4, 0}, {0, -2, 0, 4, 0}}, new double[][]{{0, -2, 0, 4, 0}, {0, -2, 0, 4, 0}}},
        };
    }

    @Test
    @UseDataProvider("getTestConstructorExceptionData")
    public void testConstructorException(double[][] values) {
        exceptionRule.expect(Exception.class);

        Matrix __ = new Matrix(values);
    }

    @DataProvider
    public static Object[][] getTestConstructorExceptionData() {
        return new Object[][]{
                {new double[0][0]},
                {null}
        };
    }

    @Test
    @UseDataProvider("getTestGetElementExceptionData")
    public void testGetElementException(Matrix a, int rowIndex, int colIndex) {
        exceptionRule.expect(IllegalStateException.class);

        double __ = a.getElement(rowIndex, colIndex);
    }

    @DataProvider
    public static Object[][] getTestGetElementExceptionData() {
        return new Object[][]{
                {new Matrix(1, 1), 1, 0},
                {new Matrix(1, 1), 0, 1},
                {new Matrix(1, 1), 1, 1},

                {new Matrix(1, 1), -1, 0},
                {new Matrix(1, 1), 0, -1},
                {new Matrix(1, 1), -1, -1},
        };
    }

    @Test
    @UseDataProvider("getTestEqualsData")
    public void testEquals(Matrix a, Object b, boolean expected) {
        boolean actual = a.equals(b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestEqualsData() {
        Matrix a = new Matrix(1, 1);
        return new Object[][]{
                {a, a, true},
                {new Matrix(new double[][]{{1, 2}}), new Matrix(new double[][]{{1, 2}}), true},
                {new Matrix(new double[][]{{1}, {2}}), new Matrix(new double[][]{{1}, {2}}), true},
                {new Matrix(new double[][]{{1}, {2}}), new Matrix(new double[][]{{1, 2}}), false},

                {new Matrix(new double[][]{{1, 2, 3}}), new Matrix(new double[][]{{-1, 2, 3}}), false},
                {new Matrix(new double[][]{{1, 2, 3}}), new Matrix(new double[][]{{1, -2, 3}}), false},

                {new Matrix(new double[][]{{1, 2}}), null, false},
                {new Matrix(new double[][]{{1, 2}}), new Object(), false},

        };
    }

    @Test
    @UseDataProvider("getTestAddData")
    public void testAdd(Matrix a, Matrix b, Matrix expected)
            throws InvalidMatrixOperationException, NullPointerException {
        Matrix actual = Matrix.add(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestAddData() {
        return new Object[][]{
                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{11, 22}, {33, 44}})},
                {new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{9, 18}, {27, 36}})},
                {new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{9, 18}, {27, 36}})},
                {new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{-10, -20}, {-30, -40}}), new Matrix(new double[][]{{-11, -22}, {-33, -44}})},

                {new Matrix(2, 2), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{10, 20}, {30, 40}})},
                {new Matrix(2, 2), new Matrix(new double[][]{{-10, -20}, {-30, -40}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-10, -20}, {-30, -40}})},

                {new Matrix(new double[][]{{1, 0}, {0, 0}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{11, 20}, {30, 40}})},

                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(2, 2)},
        };
    }

    @Test
    @UseDataProvider("getTestAddExceptionsData")
    public void testAddExceptions(Matrix a, Matrix b)
            throws NullPointerException, InvalidMatrixOperationException {
        exceptionRule.expect(Exception.class);

        Matrix __ = Matrix.add(a, b);
    }

    @DataProvider
    public static Object[][] getTestAddExceptionsData() {
        return new Object[][]{
                {new Matrix(1, 1), null},

                {new Matrix(1, 1), new Matrix(1, 2)},
                {new Matrix(1, 1), new Matrix(2, 1)},
                {new Matrix(1, 1), new Matrix(2, 2)}
        };
    }

    @Test
    @UseDataProvider("getTestSubtractData")
    public void testSubtract(Matrix a, Matrix b, Matrix expected)
            throws InvalidMatrixOperationException {
        Matrix actual = Matrix.subtract(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestSubtractData() {
        return new Object[][]{
                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-9, -18}, {-27, -36}})},
                {new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-11, -22}, {-33, -44}})},
                {new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{11, 22}, {33, 44}})},
                {new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{-10, -20}, {-30, -40}}), new Matrix(new double[][]{{9, 18}, {27, 36}})},

                {new Matrix(2, 2), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-10, -20}, {-30, -40}})},
                {new Matrix(2, 2), new Matrix(new double[][]{{-10, -20}, {-30, -40}}), new Matrix(new double[][]{{10, 20}, {30, 40}})},

                {new Matrix(new double[][]{{1, 0}, {0, 0}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-9, -20}, {-30, -40}})},

                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(2, 2)},
        };
    }

    @Test
    @UseDataProvider("getTestSubtractExceptions")
    public void testSubtractExceptions(Matrix a, Matrix b)
            throws NullPointerException, InvalidMatrixOperationException {
        exceptionRule.expect(Exception.class);

        Matrix __ = Matrix.subtract(a, b);
    }

    @DataProvider
    public static Object[][] getTestSubtractExceptions() {
        return new Object[][]{
                {new Matrix(1, 1), null},
                {new Matrix(1, 1), new Matrix(1, 2)},
                {new Matrix(1, 1), new Matrix(2, 1)},
                {new Matrix(1, 1), new Matrix(2, 2)},
        };
    }

    @Test
    @UseDataProvider("getTestMultiplication")
    public void testMultiplication(Matrix a, double value, Matrix expected) {
        Matrix actual = Matrix.multiply(a, value);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestMultiplication() {
        return new Object[][]{
                {new Matrix(new double[][]{{1, 2}, {3, 4}}), 0, new Matrix(2, 2)},
                {new Matrix(2, 2), 5, new Matrix(2, 2)},
                {new Matrix(new double[][]{{1, 2}, {3, 4}}), 5, new Matrix(new double[][]{{5, 10}, {15, 20}})}
        };
    }

    @Test
    public void testMultiplicationException() {
        exceptionRule.expect(NullPointerException.class);

        Matrix __ = Matrix.multiply(null, 5);
    }

    @Test
    @UseDataProvider("getTestMatrixMultiplication")
    public void testMatrixMultiplication(Matrix a, Matrix b, Matrix expected)
            throws InvalidMatrixOperationException {
        Matrix actual = Matrix.multiply(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestMatrixMultiplication() {
        return new Object[][]{
                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{70, 100}, {150, 220}})},
                {new Matrix(new double[][]{{-1, -2}, {-3, -4}}), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(new double[][]{{-70, -100}, {-150, -220}})},

                {new Matrix(2, 2), new Matrix(new double[][]{{10, 20}, {30, 40}}), new Matrix(2, 2)},

                {new Matrix(new double[][]{{1, 2}, {3, 4}}), new Matrix(new double[][]{{10}, {20}}), new Matrix(new double[][]{{50}, {110}})},
        };
    }

    @Test
    @UseDataProvider("getTestMatrixMultiplicationException")
    public void testMatrixMultiplicationException(Matrix a, Matrix b)
            throws NullPointerException, InvalidMatrixOperationException {
        exceptionRule.expect(Exception.class);

        Matrix __ = Matrix.multiply(a, b);
    }

    @DataProvider
    public static Object[][] getTestMatrixMultiplicationException() {
        return new Object[][]{
                {new Matrix(1, 1), null},
                {new Matrix(1, 1), new Matrix(2, 1)}
        };
    }

    @Test
    @UseDataProvider("getTestToStringData")
    public void testToString(Matrix a, String expected) {
        Assert.assertEquals(expected, a.toString());
    }

    @DataProvider
    public static Object[][] getTestToStringData() {
        return new Object[][] {
                {new Matrix(1, 1), "|    0.0    |\n"},
                {new Matrix(2, 1), "|    0.0    |\n|    0.0    |\n"},
                {new Matrix(2, 2), "|    0.0        0.0    |\n|    0.0        0.0    |\n"},
                {new Matrix(new double[][] {{1000, 2000}, {3000, 4000}}), "|    1000.0        2000.0    |\n|    3000.0        4000.0    |\n"},
                {new Matrix(new double[][] {{-100, -200}, {-300, -400}}), "|    -100.0        -200.0    |\n|    -300.0        -400.0    |\n"},
        };
    }
}
