package Tests.VectorTests;

import Vector.Vector3D;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class Vector3DTests {
    @Test
    @UseDataProvider("getTestPropertiesData")
    public void testProperties(double x, double y, double z) {
        Vector3D actual = new Vector3D(x, y, z);

        Assert.assertNotNull(actual);
        Assert.assertEquals(x, actual.getX(), 0);
        Assert.assertEquals(y, actual.getY(), 0);
        Assert.assertEquals(z, actual.getZ(), 0);
    }

    @DataProvider
    public static Object[][] getTestPropertiesData() {
        return new Object[][]{
                {30.3, 20.2, 10.1},
                {0, 0, 0},
                {-1.01, -2.02, -3.03}
        };
    }

    @Test
    @UseDataProvider("getTestEqualsData")
    public void testEquals(Vector3D vector, Object object, boolean expected) {
        boolean actual = vector.equals(object);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestEqualsData() {
        Vector3D a = new Vector3D(1, 2, 3);
        return new Object[][]{
                {a, a, true},
                {new Vector3D(1, 2, 3), new Vector3D(1, 2, 3), true},
                {new Vector3D(1, 2, 3), new Vector3D(-1, 2, 3), false},
                {new Vector3D(1, 2, 3), new Vector3D(1, -2, 3), false},
                {new Vector3D(1, 2, 3), new Vector3D(1, 2, -3), false},
                {new Vector3D(1, 2, 3), null, false},
                {new Vector3D(1, 2, 3), new Object(), false}
        };
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testEqualsException() {
        Vector3D a = null;
        Vector3D b = new Vector3D();

        exceptionRule.expect(NullPointerException.class);

        a.equals(b);
    }

//    @ParameterizedTest
//    @ValueSource(doubles = { -9, -1, 0, 1, 9 })
//    public void testHashCode(double x, double y, double z, double n, double l, double m) {
//        Vector3D a = new Vector3D(x, y, z);
//        Vector3D b = new Vector3D(n, l, m);
//        boolean expected = a.equals(b);
//
//        boolean actual = a.hashCode() == b.hashCode();
//
//        Assert.assertEquals(expected, actual);
//    }

    @Test
    @UseDataProvider("getTestToStringData")
    public void testToString(Vector3D a, String expected) {
        String actual = a.toString();
        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestToStringData() {
        return new Object[][]{
                {new Vector3D(100.0, 200.0, 300.0), "{100; 200; 300}"},
                {new Vector3D(10.0, 20.0, 30.0), "{10; 20; 30}"},
                {new Vector3D(1.0, 2.0, 3.0), "{1; 2; 3}"},
                {new Vector3D(-1, -2, -3), "{-1; -2; -3}"},
                {new Vector3D(-10, -20, -30), "{-10; -20; -30}"},
                {new Vector3D(-100, -200, -300), "{-100; -200; -300}"},
                {new Vector3D(1.1, 2.2, 3.3), "{1.1; 2.2; 3.3}"},
                {new Vector3D(1.01, 2.02, 3.03), "{1.01; 2.02; 3.03}"},
                {new Vector3D(-1.1, -2.2, -3.3), "{-1.1; -2.2; -3.3}"},
                {new Vector3D(-1.01, -2.02, -3.03), "{-1.01; -2.02; -3.03}"}
        };
    }

    @Test
    @UseDataProvider("getTestAddData")
    public void testAdd(Vector3D a, Vector3D b, Vector3D expected) {
        Vector3D actual = Vector3D.add(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestAddData() {
        return new Object[][]{
                {new Vector3D(1, 2, 3), new Vector3D(10, 20, 30), new Vector3D(11, 22, 33)},
                {new Vector3D(-1, -2, -3), new Vector3D(10, 20, 30), new Vector3D(9, 18, 27)},
                {new Vector3D(10, 20, 30), new Vector3D(-1, -2, -3), new Vector3D(9, 18, 27)},
                {new Vector3D(-1, -2, -3), new Vector3D(-10, -20, -30), new Vector3D(-11, -22, -33)},

                {new Vector3D(), new Vector3D(10, 20, 30), new Vector3D(10, 20, 30)},
                {new Vector3D(), new Vector3D(-10, -20, -30), new Vector3D(-10, -20, -30)},

                {new Vector3D(1, 0, 0), new Vector3D(10, 20, 30), new Vector3D(11, 20, 30)},
                {new Vector3D(0, 2, 0), new Vector3D(10, 20, 30), new Vector3D(10, 22, 30)},
                {new Vector3D(0, 0, 3), new Vector3D(10, 20, 30), new Vector3D(10, 20, 33)},

                {new Vector3D(1, 2, 3), new Vector3D(-1, -2, -3), new Vector3D(0, 0, 0)}
        };
    }

    @Test
    public void testAddException() {
        Vector3D a = null;
        Vector3D b = new Vector3D();

        exceptionRule.expect(NullPointerException.class);

        Vector3D.add(a, b);
    }

    @Test
    @UseDataProvider("getTestSubtractData")
    public void testSubtract(Vector3D a, Vector3D b, Vector3D expected) {
        Vector3D actual = Vector3D.subtract(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestSubtractData() {
        return new Object[][]{
                {new Vector3D(1, 2, 3), new Vector3D(10, 20, 30), new Vector3D(-9, -18, -27)},
                {new Vector3D(-1, -2, -3), new Vector3D(10, 20, 30), new Vector3D(-11, -22, -33)},
                {new Vector3D(10, 20, 30), new Vector3D(-1, -2, -3), new Vector3D(11, 22, 33)},
                {new Vector3D(-1, -2, -3), new Vector3D(-10, -20, -30), new Vector3D(9, 18, 27)},

                {new Vector3D(), new Vector3D(10, 20, 30), new Vector3D(-10, -20, -30)},
                {new Vector3D(), new Vector3D(-10, -20, -30), new Vector3D(10, 20, 30)},

                {new Vector3D(1, 0, 0), new Vector3D(10, 20, 30), new Vector3D(-9, -20, -30)},
                {new Vector3D(0, 2, 0), new Vector3D(10, 20, 30), new Vector3D(-10, -18, -30)},
                {new Vector3D(0, 0, 3), new Vector3D(10, 20, 30), new Vector3D(-10, -20, -27)},

                {new Vector3D(1, 2, 3), new Vector3D(-1, -2, -3), new Vector3D(0, 0, 0)}
        };
    }

    @Test
    public void testSubtractException() {
        Vector3D a = null;
        Vector3D b = new Vector3D();

        exceptionRule.expect(NullPointerException.class);

        Vector3D.subtract(a, b);
    }

    @Test
    @UseDataProvider("getTestMultiplicationData")
    public void testMultiplication(Vector3D a, Vector3D b, double expected) {
        double actual = Vector3D.multiply(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @DataProvider
    public static Object[][] getTestMultiplicationData() {
        return new Object[][]{
                {new Vector3D(10, 20, 30), new Vector3D(1, 2, 3), 140},
                {new Vector3D(10, 20, 30), new Vector3D(-1, -2, -3), -140},
                {new Vector3D(-1, -2, -3), new Vector3D(-10, -20, -30), 140},

                {new Vector3D(), new Vector3D(10, 20, 30), 0},
                {new Vector3D(), new Vector3D(), 0},

                {new Vector3D(1, 0, 0), new Vector3D(10, 0, 30), 10},
                {new Vector3D(0, 2, 0), new Vector3D(10, 0, 30), 0},
                {new Vector3D(0, 0, 3), new Vector3D(10, 0, 30), 90},
        };
    }

    @Test
    public void testMultiplicationException() {
        Vector3D a = null;
        Vector3D b = new Vector3D();

        exceptionRule.expect(NullPointerException.class);

        Vector3D.multiply(a, b);
    }

    @Test
    @UseDataProvider("getTestCrossMultiplicationData")
    public void testCrossMultiplication(Vector3D a, Vector3D b, Vector3D expected) {
        Vector3D actual = Vector3D.cross(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestCrossMultiplicationData() {
        return new Object[][]{
                {new Vector3D(10, 20, 30), new Vector3D(1, 2, 3), new Vector3D()},
                {new Vector3D(10, 20, 31), new Vector3D(-1, -2, -3), new Vector3D(2, -1, 0)},
                {new Vector3D(-1, -2, -5), new Vector3D(-10, -20, -30), new Vector3D(-40, 20, 0)},

                {new Vector3D(), new Vector3D(10, 20, 30), new Vector3D()},
                {new Vector3D(), new Vector3D(), new Vector3D()},

                {new Vector3D(0, 2, 0), new Vector3D(10, 0, 30), new Vector3D(60, 0, -20)},
                {new Vector3D(1, 0, 0), new Vector3D(10, 0, 30), new Vector3D(0, -30, 0)},
                {new Vector3D(0, 0, 3), new Vector3D(10, 0, 30), new Vector3D(0, 30, 0)}

        };
    }

    @Test
    public void testCrossMultiplicationException() {
        Vector3D a = null;
        Vector3D b = new Vector3D();

        exceptionRule.expect(NullPointerException.class);

        Vector3D.cross(a, b);
    }

    @Test
    @UseDataProvider("getTestEqualityOperatorData")
    public void testEqualityOperator(Vector3D a, Vector3D b, boolean expected) {
        boolean actual = Vector3D.equals(a, b);

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] getTestEqualityOperatorData() {
        Vector3D a = new Vector3D(1, 2, 3);
        return new Object[][]{
                {a, a, true},
                {new Vector3D(1, 2, 3), new Vector3D(1, 2, 3), true},
                {new Vector3D(1, 2, 3), new Vector3D(-1, 2, 3), false},
                {new Vector3D(1, 2, 3), new Vector3D(1, -2, 3), false},
                {new Vector3D(1, 2, 3), new Vector3D(1, 2, -3), false},
                {new Vector3D(1, 2, 3), null, false}
        };
    }
}
