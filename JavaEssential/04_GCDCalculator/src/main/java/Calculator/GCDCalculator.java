package Calculator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

public class GCDCalculator {
    public static int calculateEuclidean(int a, int b) {
        int smallest = Math.min(a, b);
        int biggest = Math.max(a, b);

        if (smallest == 0)
            return biggest;
        else
            return calculateEuclidean(smallest, biggest - smallest);
    }

    public static int calculateEuclidean(int... values) {
        if (values == null)
            throw new IllegalArgumentException("values");

        if (values.length < 2)
            throw new IllegalStateException("НОД вычисляется минимум для двух чисел.");

        int gcd = values[0];
        for (int i = 1; i < values.length; i++)
            gcd = calculateEuclidean(gcd, values[i]);

        return gcd;
    }

    public static int calculateStein(int a, int b) {
        int smallest = Math.min(a, b);
        int biggest = Math.max(a, b);

        if (smallest == biggest)
            return smallest;

        if (smallest == 0)
            return biggest;

        if (biggest == 0)
            return smallest;

        if (smallest % 2 == 0 && biggest % 2 == 0)
            return 2 * calculateStein(smallest / 2, biggest / 2);

        if (smallest % 2 == 0 && biggest % 2 != 0)
            return calculateStein(smallest / 2, biggest);

        if (biggest % 2 == 0 && smallest % 2 != 0)
            return calculateStein(smallest, biggest / 2);

        return calculateStein((biggest - smallest) / 2, smallest);
    }

    public static int calculateStein(int... values) {
        if (values == null)
            throw new IllegalArgumentException("values");

        if (values.length < 2)
            throw new IllegalStateException("НОД выисляется минимум для двух чисел.");

        int gcd = values[0];
        for (int i = 1; i < values.length; i++)
            gcd = calculateStein(gcd, values[i]);

        return gcd;
    }

    public static int calculateStein(AtomicReference<Duration> result, int... values) {
        LocalTime startTime = LocalTime.now();
        int gcd = calculateStein(values);
        LocalTime endTime = LocalTime.now();

        result.set(Duration.between(startTime, endTime));

        return gcd;
    }

    public static int calculateEuclidean(AtomicReference<Duration> result, int... values) {
        LocalTime startTime = LocalTime.now();
        int gcd = calculateEuclidean(values);
        LocalTime endTime = LocalTime.now();

        result.set(Duration.between(startTime, endTime));
        return gcd;
    }
}
