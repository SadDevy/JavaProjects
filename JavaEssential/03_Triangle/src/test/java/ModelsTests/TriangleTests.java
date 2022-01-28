package ModelsTests;

import Models.Triangle;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TriangleTests {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCouldExists_aZero_failure() {
        final double a = 0;
        final double b = 0;
        final double c = 0;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_aNegative_failure() {
        final double a = -3;
        final double b = 4;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_bigA_failure() {
        final double a = 300;
        final double b = 4;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_bZero_failure() {
        final double a = 4;
        final double b = 0;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_bNegative_failure() {
        final double a = 3;
        final double b = -4;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_bigB_failure() {
        final double a = 3;
        final double b = 400;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_cZero_failure() {
        final double a = 4;
        final double b = 5;
        final double c = 0;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_cNegative_failure() {
        final double a = 3;
        final double b = 4;
        final double c = -5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_bigC_failure() {
        final double a = 3;
        final double b = 4;
        final double c = 500;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertFalse(actual);
    }

    @Test
    public void testCouldExist_equilateralTriangleSides_success() {
        final double a = 1;
        final double b = 1;
        final double c = 1;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertTrue(actual);
    }

    @Test
    public void testCouldExist_rectangularTriangleSides_success() {
        final double a = 3;
        final double b = 4;
        final double c = 5;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertTrue(actual);
    }

    @Test
    public void testCouldExist_isoscelesTriangleSides_success() {
        final double a = 2;
        final double b = 2;
        final double c = 3;

        boolean actual = Triangle.couldExist(a, b, c);

        Assert.assertTrue(actual);
    }

    @Test
    public void testCreateTriangle_equilateralTriangleSides_success() {
        Triangle triangle = Triangle.createTriangle(1, 1, 1);

        Assert.assertNotNull(triangle);
    }

    @Test
    public void testCreateTriangle_triangleNotExists_exception() {
        final double a = 1;
        final double b = 2;
        final double c = 300;
        String expected = String.format("Треугольник не может существовать со сторонами: %f, %f, %f.", a, b, c);

        exceptionRule.expect(IllegalStateException.class);
        exceptionRule.expectMessage(expected);

        Triangle triangle = Triangle.createTriangle(a, b, c);
    }

    @Test
    public void testCreateTriangle_triangleHasThreeSides_success() {
        final double a = 3;
        final double b = 4;
        final double c = 5;

        Triangle triangle = Triangle.createTriangle(a, b, c);

        Assert.assertEquals(a, triangle.getA(), 0);
        Assert.assertEquals(b, triangle.getB(), 0);
        Assert.assertEquals(c, triangle.getC(), 0);
    }

    @Test
    public void testCalculatePerimeter_rectangularTriangleSides_success() {
        Triangle triangle = Triangle.createTriangle(3, 4, 5);
        final double expected = 12;

        double actual = triangle.calculatePerimeter();

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateArea_rectangularTriangleSides_success() {
        Triangle triangle = Triangle.createTriangle(3, 4, 5);
        final double expected = 6;

        double actual = triangle.calculateArea();

        Assert.assertEquals(expected, actual, 0);
    }
}
