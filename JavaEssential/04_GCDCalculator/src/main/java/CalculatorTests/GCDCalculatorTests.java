package CalculatorTests;

import Calculator.GCDCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class GCDCalculatorTests {
    @Test
    public void testCalculateEuclidean_bothEqual_success() {
        final int a = 320;
        final int b = 320;
        final int expected = 320;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_aLess_success() {
        final int a = 120;
        final int b = 320;
        final int expected = 40;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_bLess_success() {
        final int a = 320;
        final int b = 120;
        final int expected = 40;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_bothEven_success() {
        final int a = 4;
        final int b = 356;
        final int expected = 4;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_bUneven_success() {
        final int a = 4;
        final int b = 11;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_bothUneven_success() {
        final int a = 11;
        final int b = 251;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_aUneven_success() {
        final int a = 11;
        final int b = 12;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_firstFiveSimpleWithoutOne_success() {
        final int a = 3;
        final int b = 5;
        final int c = 7;
        final int d = 11;
        final int e = 13;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b, c, d, e);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_fourValues_success() {
        final int a = 653;
        final int b = 166;
        final int c = 12;
        final int d = 15;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b, c, d);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateEuclidean_fiveValues_success() {
        final int a = 653;
        final int b = 166;
        final int c = 12;
        final int d = 15;
        final int e = 357;
        final int expected = 1;

        int actual = GCDCalculator.calculateEuclidean(a, b, c, d, e);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_bothEqual_success() {
        final int a = 320;
        final int b = 320;
        final int expected = 320;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_aLess_success() {
        final int a = 120;
        final int b = 320;
        final int expected = 40;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_bLess_success() {
        final int a = 320;
        final int b = 120;
        final int expected = 40;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_bothEven_success() {
        final int a = 56;
        final int b = 664;
        final int expected = 8;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_aUneven_success() {
        final int a = 49;
        final int b = 56;
        final int expected = 7;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_bothUneven_success() {
        final int a = 11;
        final int b = 121;
        final int expected = 11;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_bUneven_success() {
        final int a = 10;
        final int b = 121;
        final int expected = 1;

        int actual = GCDCalculator.calculateStein(a, b);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateStein_fifteenValues_success() {
        int[] values = { 300, 600, 18, 9, 3, 36, 72, 324, 6, 27, 15, 33, 30, 66, 54 };
        final int expected = 3;

        AtomicReference<Duration> durationReference = new AtomicReference<>();
        int actual = GCDCalculator.calculateStein(durationReference, values);

        Assert.assertEquals(expected, actual, 0);
        Assert.assertTrue(durationReference.get().toMillis() >= 0);
    }

    @Test
    public void testCalculateEuclidean_fifteenValues_success() {
        int[] values = { 300, 600, 18, 9, 3, 36, 72, 324, 6, 27, 15, 33, 30, 66, 54 };
        final int expected = 3;

        AtomicReference<Duration> durationReference = new AtomicReference<>();
        int actual = GCDCalculator.calculateEuclidean(durationReference, values);

        Assert.assertEquals(expected, actual, 0);
        Assert.assertTrue(durationReference.get().toMillis() >= 0);
    }
}
