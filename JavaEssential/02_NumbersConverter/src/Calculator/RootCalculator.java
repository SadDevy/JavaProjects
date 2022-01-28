package Calculator;

public class RootCalculator {
    public static double calculateRootNewton(double value, double n, double accuracy) {
        double a = value / n;
        double b = calculateNewValue(value, a, n);
        while (!equals(b, a, accuracy)) {
            a = b;
            b = calculateNewValue(value, a, n);
        }

        return b;
    }

    public static boolean equals(double a, double b, double accuracy) {
        return Math.abs(a - b) < accuracy;
    }

    private static double calculateNewValue(double number, double oldValue, double n) {
        return (1 / n) * ((n - 1) * oldValue + (number / (Math.pow(oldValue, n - 1))));
    }

    public static double calculateRoot(double value, double n) {
        return Math.pow(value, 1 / n);
    }
}
